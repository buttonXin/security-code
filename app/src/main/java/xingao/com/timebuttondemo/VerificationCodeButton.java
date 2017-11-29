package xingao.com.timebuttondemo;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;


/**
 * 验证码 view
 *
 * @author Administrator
 * @date 2016/10/7 0007
 */

public class VerificationCodeButton extends AppCompatButton implements View.OnClickListener {

    private long mCountSumTime = 60;
    //当前只支持1秒 1秒递减，就算写了3秒， 也是等3秒在设置text的...(就是59等3秒后 在显示56 等3秒 53)
    private long mCountDownInterval = 1;

    private View.OnClickListener mListener;

    private TimeTextDown mTimeTextDown;
    //默认显示  获取验证码
    private String mTextStart = "获取验证码";
    //默认显示  计时的text  秒
    private String mTextDown = "秒" ;

    public VerificationCodeButton(Context context) {
        this(context , null);

    }
    public VerificationCodeButton(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public VerificationCodeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mTimeTextDown = new TimeTextDown(this, mCountSumTime * 1000 , mCountDownInterval * 1000);
        setOnClickListener(this);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if (l instanceof VerificationCodeButton) {
            super.setOnClickListener(l);
        } else {
            mListener = l;
        }
    }

    /**
     * 点击按钮后的操作
     */
    @Override
    public void onClick(View v) {
        start();
        if (mListener != null) {
            mListener.onClick(v);
        }
    }


    /**
     * 直接调用此方法 ，开始计时
     * */
    public void start(){
        if (mTimeTextDown != null) {
            mTimeTextDown.start();
        }
    }

    /**
     * 在时间没有到 0 的时候 销毁Activity，需要调用此方法，否则会内存泄露 , onDestroy调用就行
     */
    public void stop(){
        if (mTimeTextDown != null) {
            mTimeTextDown.onFinish();
        }

    }

    /**
     *
     * @param countSumTime 设置总时间（单位秒） ，默认60秒
     */
    public void setSumTime(long countSumTime){
        mTimeTextDown = new TimeTextDown(this, countSumTime * 1000 , mCountDownInterval * 1000);
    }

    /**
     *
     * @param countSumTime 设置总时间
     * @param countDownInterval 间隔时间（单位秒）
     */
    public void setSumTime(long countSumTime , long countDownInterval){
        mTimeTextDown = new TimeTextDown(this, countSumTime * 1000 , countDownInterval * 1000);
    }


    /**
     * 这是通过时间设置来改变button的内容
     * */
    public class TimeTextDown extends CountDownTimer {
        private Button mButton ;
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeTextDown(Button button , long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mButton  = button;
        }
        //开始时间 及执行倒计时过程，这里可以设置button显示内容
        @Override
        public void onTick(long millisUntilFinished) {
            String textTiem = (int)millisUntilFinished / 1000 + mTextDown;
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
                mButton.setText(mTextStart);
            }
            this.cancel();

        }
    }
}