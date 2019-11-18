package com.example.passwordlayout;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView resultTextView;
    private EditText sourceTextView;
    private View copyButton;
    private PasswordsHelper helper;
    private ImageView quality;
    private TextView qualityTextView;
    private CompoundButton addUppercase;
    private CompoundButton addNumbers;
    private CompoundButton addSpecialSymbols;
    private SeekBar seekNumberOfSymbols;
    private TextView lengthOfPassword;
    private PasswordGenerator passwordGenerator;
    private TextView newPasswordText;
    private Button buttonForGenerationNewPassword;
    private int symbolsInt = 8;
    private View buttonForCopyNewPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new PasswordsHelper(
                getResources().getStringArray(R.array.russian),
                getResources().getStringArray(R.array.english)
        );

        passwordGenerator = new PasswordGenerator();

        resultTextView = findViewById(R.id.result_text);
        sourceTextView = findViewById(R.id.source_text);
        copyButton = findViewById(R.id.button_copy);
        quality = findViewById(R.id.quality);
        qualityTextView = findViewById(R.id.quality_text);
        addUppercase = findViewById(R.id.check_uppercase);
        addNumbers = findViewById(R.id.add_numbers);
        addSpecialSymbols = findViewById(R.id.add_special_symbols);
        seekNumberOfSymbols = findViewById(R.id.seek_number_of_symbols);
        lengthOfPassword = findViewById(R.id.length_of_password);
        newPasswordText = findViewById(R.id.new_password_text);
        buttonForGenerationNewPassword = findViewById(R.id.button_for_generation_new_password);
        buttonForCopyNewPasswords = findViewById(R.id.button_copy_for_new_password);





        copyButton.setEnabled(false);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager =
                        (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText(
                        MainActivity.this.getString(R.string.clipboard_title),
                        resultTextView.getText()
                ));

                Toast.makeText(MainActivity.this, R.string.message_copied,
                        Toast.LENGTH_SHORT).show();
            }
        });


        sourceTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultTextView.setText(helper.convert(s));
                copyButton.setEnabled(s.length() > 0);
                int qualityInt = helper.getQuality(s);
                quality.setImageLevel(qualityInt*1000);
                qualityTextView.setText(getResources().getStringArray(R.array.qualities)[qualityInt]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        seekNumberOfSymbols.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String symbols = getResources()
                        .getQuantityString(R.plurals.symbols, progress+8, progress);
                symbolsInt = Integer.parseInt(symbols.replaceAll("\\D","")) + 8;
                lengthOfPassword.setText(symbolsInt + symbols.replaceAll("\\d",""));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonForGenerationNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPasswordText.setText(passwordGenerator.generate(addUppercase.isChecked(), addNumbers.isChecked(),
                        addSpecialSymbols.isChecked(), symbolsInt));
            }
        });

        buttonForCopyNewPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager =
                        (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText(
                        MainActivity.this.getString(R.string.clipboard_title),
                        newPasswordText.getText()
                ));

                Toast.makeText(MainActivity.this, R.string.message_copied,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
