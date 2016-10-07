package xingao.com.timebuttondemo;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/7 0007.
 */

public class SecurityButton extends Button implements View.OnClickListener{

    private long millisInFuture = 60;
    private long countDownInterval = 1;//当前只支持1秒 1秒递减，就算写了3秒， 也是等3秒在设置text的...
    private StartTimeLIstener mLIstener;//可以有回调，不过不用也行的


    public SecurityButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public SecurityButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public SecurityButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mLIstener.onStart();
        TimeTextButton timeTextButton = new TimeTextButton(this,millisInFuture * 1000 , countDownInterval * 1000);
        timeTextButton.start();

    }

    /**
     * 可以通过使用setOnClickListener里面直接调用此方法
     * */
    public void start(){
        TimeTextButton timeTextButton = new TimeTextButton(this,millisInFuture * 1000 , countDownInterval * 1000);
        timeTextButton.start();
    }
    /**
     * 这是回调使用，可以不需要使用
     * */
    public void setStartTime(StartTimeLIstener lIstener){
        mLIstener = lIstener;
    }

    public interface StartTimeLIstener{
        void onStart();
    }

    /**
     * 设置总时间（单位秒） 尽量用这个了，默认60秒
     * */
    public void setTime(long millisInFuture){
        this.millisInFuture = millisInFuture ;
    }
    /**
     * 设置总时间 和 间隔时间（单位秒）
     * */
    public void setTime(long millisInFuture , long countDownInterval){
        this.millisInFuture = millisInFuture ;
        this.countDownInterval = countDownInterval;
    }

    /**
     * 这是通过时间设置来改变button的内容
     * */
    public class TimeTextButton extends CountDownTimer {
        private Button mButton ;
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeTextButton(Button button , long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mButton  = button;
        }
        //开始时间 及执行倒计时过程，这里可以设置button显示内容
        @Override
        public void onTick(long millisUntilFinished) {
            String textTiem = (int)millisUntilFinished / 1000 +"s";
            if (mButton != null){
                mButton.setEnabled(false);
                mButton.setText(textTiem );
            }

        }
        //结束时间，设置结束后的button内容
        @Override
        public void onFinish() {
            if (mButton != null){
                mButton.setEnabled(true);
                mButton.setText("获取验证码");
            }

        }
    }
}