package com.example.a04_09chapter8event

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a04_09chapter8event.databinding.ActivityMainBinding


//방법 2 메인 액티비티 안에 직접 컴파운드버튼을 구현(인터페이스를 구현)
class MainActivity : AppCompatActivity() {
    //사용자가 시간을 입력하면 해당 초부터 카운트다운 시작

    var targetTimeMillis = 0L //목표시간(밀리초)
    var remainTimeMillis = 0L //남은시간(밀리초
    var isRunning = false //타이머 실행 여부


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startButton.setOnClickListener {
            if(!isRunning){ //타이머가 작동중이 아니고,
                //EditTExt에서 입력받은 값을 가져오고 string -> (널처리를 위해) IntorNull
                val inputSecond = binding.timeInput.text.toString().toIntOrNull()
                //유효한 시간인지 확인, 빈칸이 아니고, 0보다 커여함
                if (inputSecond != null&&inputSecond > 0){
                    targetTimeMillis = inputSecond * 1000L
                    remainTimeMillis = targetTimeMillis
                    startTimer(binding) // this
                }
            } else { //EditText에 음수를 입력하거나, 입력하지 않았을 때의 처리
                binding.timeInput.error = "유효한 시간을 입력하세요!"  //editText의 error 속성을 다음과 같이 바꿀 수 있음

            }
        }
        binding.stopButton.setOnClickListener {
            stopTimer(binding)
        }
        binding.resetButton.setOnClickListener {
            resetTimer(binding)
        }

    }
    fun startTimer(binding: ActivityMainBinding){
        isRunning = true
        binding.choromnometer.base = SystemClock.elapsedRealtime() + remainTimeMillis //10초를 입력하면 10초가 저장
        binding.choromnometer.start()

        updateButtonStates(binding, true) //버튼상태처리함수

        binding.choromnometer.setOnChronometerTickListener{
            val elapsedMillis = SystemClock.elapsedRealtime() - binding.choromnometer.base
            if(elapsedMillis >=0){ //멈춤 처리 -10 -> 0
                stopTimer(binding)
                binding.choromnometer.text = "00:00"
                isRunning = false
                updateButtonStates(binding, false)
            }
        }
    }
    //크로노미터 멈추고, 남은 시간계산, 버튼 상태 변경
    fun stopTimer(binding: ActivityMainBinding){
        isRunning = false
        remainTimeMillis = binding.choromnometer.base - SystemClock.elapsedRealtime() //남은 시간 게산
        binding.choromnometer.stop()
        updateButtonStates(binding, false)


    }fun resetTimer(binding: ActivityMainBinding){
        isRunning = false
        //타겟타입, 리메인타임 초기화
        targetTimeMillis = 0L
        remainTimeMillis = 0L

        //크로노미터 멈추고, 실제시간, 텍스트로표시되는 시간 초기화
        binding.choromnometer.base = SystemClock.elapsedRealtime()
        binding.choromnometer.stop()
        binding.choromnometer.text ="00:00"

        //EditText 초괴화 후 버튼 상태 변경
        binding.timeInput.text.clear()
        updateButtonStates(binding, false)

    }


    fun updateButtonStates(binding: ActivityMainBinding, running: Boolean){
        //running 이 불린 값으로 불러와짐
        binding.startButton.isEnabled = !running && remainTimeMillis > 0L
        binding.stopButton.isEnabled = running
        binding.resetButton.isEnabled = !running && targetTimeMillis >0L
    }
}
