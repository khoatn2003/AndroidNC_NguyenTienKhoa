package com.example.btvn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        VibratorManager vibratorManager = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        Vibrator vibrator = vibratorManager.getDefaultVibrator();
        // Thay Long.MAX_VALUE bằng thời gian hợp lý hoặc sử dụng repeat = 0 để lặp lại rung
        vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE)); // rung 5 giây
        //vibrator.vibrate(VibrationEffect.createOneShot(Long.MAX_VALUE, VibrationEffect.DEFAULT_AMPLITUDE));
    }
}
