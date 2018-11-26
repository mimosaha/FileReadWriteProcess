package portal.remote.superpeer.remote.com.filereadwriteprocess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputBox = findViewById(R.id.input_box);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = inputBox.getText().toString();
                if (!TextUtils.isEmpty(inputText)) {
                    FileProcess.getInstance().saveInfoToSD(inputText);
                    inputBox.setText("");
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileInfo = FileProcess.getInstance().readFromSD();
                Log.v("File_Process: ", "Info: " + fileInfo);
            }
        });



    }
}
