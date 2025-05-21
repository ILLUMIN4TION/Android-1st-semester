package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    //아이템 자체를 꾸미거나, 아이템 사이의 여백을 꾸미는데에 사용

    override fun getItemOffsets(
        outRect: Rect, //**여백을 나타내는 사각형 객체 -> 상하좌우
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1 // +1 하는 이유는 인덱스가 0부터 시작하기 떄문

        if(index% 3== 0){
            outRect.set(10,10,10,60) //l, t, r, b 3의 배수마다 아래쪽 배수를 60으로 설정
        }else{
            outRect.set(10,10,10,0)
        }
        view.setBackgroundColor(Color.LTGRAY) //연회색 설정
        ViewCompat.setElevation(view, 20.0f) // 입체감 설정( 그림자 추가)
    }
}