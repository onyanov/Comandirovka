package exzhnik.comandirovka;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateFrom;
    private TextView dateTill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText placeInput = findViewById(R.id.place_price);

        CheckBox usePlace = findViewById(R.id.use_place);
        usePlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                placeInput.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        dateFrom = findViewById(R.id.dateFrom);
        dateTill = findViewById(R.id.dateTill);

        setUpDates(dateFrom, dateTill);
    }

    private void setUpDates(TextView dateFrom, TextView dateTill) {
        View.OnClickListener dateOnClickListener = new View.OnClickListener() {

            public int mDay;
            public int mMonth;
            public int mYear;

            @Override
            public void onClick(final View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox


                                if (year < mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear, mMonth, mDay);

                                c.set(year, monthOfYear, dayOfMonth);

                                String myFormat = "dd-MM-yyyy"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                String dateForDisplay = sdf.format(c.getTime());
                                ((EditText) v).setText(dateForDisplay);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        };

        dateFrom.setOnClickListener(dateOnClickListener);
        dateTill.setOnClickListener(dateOnClickListener);

    }
}
