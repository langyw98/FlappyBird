package cn.bdqn.flappybird.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.bdqn.flappybird.R;
import cn.bdqn.flappybird.view.BackgroudView;

public class MainActivity extends Activity {

	private BackgroudView backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundView = (BackgroudView) findViewById(R.id.background_view);
    }
    
    public void BtnOnClick(View view){
    	BackgroudView background = (BackgroudView) findViewById(R.id.background_view);
    	background.switchSroll();
    }
}
