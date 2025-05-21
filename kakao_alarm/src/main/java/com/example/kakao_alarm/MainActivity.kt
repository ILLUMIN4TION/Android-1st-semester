package com.example.kakao_alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kakao_alarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //notification 권한 요청을 위한 런처, reqeust launcher 사용\
        val permissionLaucher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){ permission ->
            //퍼미션에 있는 모든 요소를 가져오고,
            val grantedAll = permission.entries.all {it.value}
            if (grantedAll ==true){
                noti() //알림생성
            }else{
                Toast.makeText(this, "권한이 거부되었습니다",Toast.LENGTH_SHORT).show()
            }
        }

        //주의사항 APi 레벨에 따른 호환성 문제 때문에 버전 체크를 해야함 API 레벨 33미만과 이상에서 분기처리
        binding.notificationButton.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                //알림 권한 필요 (POST_NOTIFICATIONS
                val hasPermission = ContextCompat.checkSelfPermission(
                    this,android.Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if(hasPermission){
                    noti()
                }else{ //권한이 없으면 권한요청 다시
                    permissionLaucher.launch(
                        arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)
                    )
                }
            }else{

            }

        }


    }

    fun noti(){
        //알람을 구성하고 표시하는 메서드
        //아이콘, 제목, 본문, 큰, 아이콘 설정
        //인라인 답장 기능 추가
        //notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        //안드로이드 버전 체크 26(오레오) 이상인지 쳌
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "one-channel"
            val channelName = "My Channel One"

            //채널 중요도
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT

            ).apply {
                description = "앱의 모든 알림을 이 채널로 보냅시다"
                setShowBadge(true)//홈 화면에 배치 후 표시하기
                val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                //사운드 재생 속성 설정
                val audioAttributes = AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
                setSound(soundUri, audioAttributes)
                enableVibration(true)//진동처리

                //알람 세부세팅

            } //apply
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        }else{
            builder = NotificationCompat.Builder(this) //channel 없이 알림 생성
        }

        //알림 빌더의 속성 체크
        builder.apply {
            //아이콘, 텍스트 설정..
            setSmallIcon(R.drawable.small)
            setWhen(System.currentTimeMillis())
            setContentTitle("홍길동 님에게 온 메시지")
            setContentText("[웹발신] 이 메시지는 웹에서 전달되었습니다")
            //스몰아이콘은 상세정보보기를 안했을 떄의 아이콘, 라지 아이콘은 상세보기를 헀을 때 나오는 아이콘
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
        }
        
        //인라인 답장 구현 우리가 답장 버튼을 눌렀을 떄의 처리
        
        val KEY_TEXT_REPLY = "key_text_reply"
        val replyLabel = "메시지 입력" //사요자에게 보여줄 힌트
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).setLabel(replyLabel).build()
        
        //답장 클릭 -> 인텐트 생성후 broadCast호출
        val replyIntent = Intent(this, ReplyReceiver::class.java) 
        val replyPendingIntent = PendingIntent.getBroadcast(
            this,
            30, //request code api 30이상에서
            replyIntent,
            PendingIntent.FLAG_MUTABLE
        ) // 답장 버튼을 누르기전에 미리 
        
        builder.addAction(
            NotificationCompat.Action.Builder(
                R.drawable.send,
                "답장",
                replyPendingIntent
            )
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true) //시스템이 답장을 추천할 수 있음 (메시지 입력했을 떄 헉, 네 등등)
                .build()
        )
        
        manager.notify(
            /*id */11,
            builder.build() //알림 실행/호출
            
        )
        
    } //noti

    

} // class

