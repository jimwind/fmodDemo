package cn.onestravel.ndk.playsound;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import org.fmod.core.EffectUtils;

import java.io.File;

/**
 * 仿QQ变声
 */
public class EffectActivity extends Activity {
    private EffectUtils effectUtils;
    private boolean permission = false;
    private String recordFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Record/audio_tool/local_record.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect);
        org.fmod.FMOD.init(this);
        effectUtils = new EffectUtils();
        requestPermission();
    }

    @Override
    protected void onDestroy() {
        org.fmod.FMOD.close();
        super.onDestroy();
    }

    public void onClick(View view) {
        File file = new File(recordFilePath);
        if(!file.exists()){
            Log.i("jimwind", "no file: "+recordFilePath);
            return;
        } else {
            Log.i("jimwind", "file exists: "+recordFilePath);
        }
        switch (view.getId()) {
            case R.id.normalLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_NORMAL);
                break;
            case R.id.luoliLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_LUOLI);
                break;
            case R.id.dashuLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_DASHU);
                break;
            case R.id.gaoguaiLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_GAOGUAI);
                break;
            case R.id.konglingLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_KONGLING);
                break;
            case R.id.jingsongLL:
                effectUtils.effect(recordFilePath, EffectUtils.MODE_JINGSONG);
                break;
        }
    }

    /**
     * 获取权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(perms[1]) == PackageManager.PERMISSION_DENIED) {
                permission = false;
                requestPermissions(perms, 200);

            } else {
                permission = true;
            }
        }
        Log.e("jimwind", "permission "+permission);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permission = true;
            }
        }
    }
}
