package portal.remote.superpeer.remote.com.filereadwriteprocess;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Mimo Saha on [26-Nov-2018 at 1:45 PM].
 * * Email: mimosaha@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: FileReadWriteProcess.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [26-Nov-2018 at 1:45 PM].
 * * --> <Second Editor> on [26-Nov-2018 at 1:45 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [26-Nov-2018 at 1:45 PM].
 * * --> <Second Reviewer> on [26-Nov-2018 at 1:45 PM].
 * * ============================================================================
 **/
public class FileProcess {

    private static FileProcess fileProcess = new FileProcess();
    private String fileName = "demo_file.txt";
    private String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static FileProcess getInstance() {
        return fileProcess;
    }

    public void saveInfoToSD(String savedData) {
        String infoPath = EXTERNAL_STORAGE_DIRECTORY + fileName;

        try {
            File FileInfo = new File(infoPath);

            if (!FileInfo.exists()) {

                FileInfo.createNewFile();

                FileOutputStream fileOutputStream = new FileOutputStream(FileInfo);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                outputStreamWriter.append(savedData);
                outputStreamWriter.close();
                fileOutputStream.close();

            } else {

                FileInputStream fileInputStream = new FileInputStream(FileInfo);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(fileInputStream));

                String aDataRow = "", aBuffer = "";
                while ((aDataRow = bufferedReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }

                aBuffer = aBuffer + savedData;
                bufferedReader.close();

                FileOutputStream fileOutputStream = new FileOutputStream(FileInfo);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                outputStreamWriter.append(aBuffer);
                outputStreamWriter.close();
                fileOutputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromSD() {

        String infoPath = EXTERNAL_STORAGE_DIRECTORY + fileName;

        try {
            File itemFile = new File(infoPath);
            if (!itemFile.exists())
                return null;

            FileInputStream fileInputStream = new FileInputStream(itemFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String aDataRow = "", aBuffer = "";

            while ((aDataRow = bufferedReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            bufferedReader.close();
            return aBuffer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
