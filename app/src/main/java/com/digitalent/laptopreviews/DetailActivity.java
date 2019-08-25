package com.digitalent.laptopreviews;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.digitalent.laptopreviews.model.Laptop;
import com.digitalent.laptopreviews.utils.Constants;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Laptop laptop = (Laptop) getIntent().getSerializableExtra(Constants.STATE_LAPTOP);
        TextView detailDescription = findViewById(R.id.detail_description);
        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailPrice = findViewById(R.id.detail_price);
        TextView detailCpu = findViewById(R.id.detail_cpu);
        TextView detailGpu = findViewById(R.id.detail_gpu);
        TextView detailDisplay = findViewById(R.id.detail_display);
        TextView detailStorage = findViewById(R.id.detail_storage);
        TextView detailRam = findViewById(R.id.detail_ram);
        TextView detailBattery = findViewById(R.id.detail_battery);
        TextView detailDimensions = findViewById(R.id.detail_dimensions);
        TextView detailWeight = findViewById(R.id.detail_weight);
        TextView detailSource = findViewById(R.id.detail_source);

        String title = "Something Happened";
        String description = "Something bad happened.";
        String image = "https://freefrontend.com/assets/img/html-funny-404-pages/Darknet-404-Page-Concept.png";
        String price = this.getString(R.string.dummy_price);
        String cpu = this.getString(R.string.dummy_value);
        String gpu = this.getString(R.string.dummy_value);
        String display = this.getString(R.string.dummy_value);
        String storage = this.getString(R.string.dummy_value);
        String ram = this.getString(R.string.dummy_value);
        String battery = this.getString(R.string.dummy_value);
        String dimensions = this.getString(R.string.dummy_value);
        String weight = this.getString(R.string.dummy_value);
        String source = this.getString(R.string.dummy_value);
        if (laptop != null) {
            title = laptop.getName();
            description = laptop.getDescription();
            image = laptop.getImage();
            price = laptop.getPrice();
            cpu = laptop.getCpu();
            gpu = laptop.getGpu();
            display = laptop.getDisplay();
            storage = laptop.getStorage();
            ram = laptop.getRam();
            battery = laptop.getBattery();
            dimensions = laptop.getDimensions();
            weight = laptop.getWeight();
            source = laptop.getSource();
        }
        setTitle(title);
        detailDescription.setText(description);
        Glide.with(this).load(image).into(detailImage);
        detailPrice.setText(price);
        detailCpu.setText(cpu);
        detailGpu.setText(gpu);
        detailDisplay.setText(display);
        detailStorage.setText(storage);
        detailRam.setText(ram);
        detailBattery.setText(battery);
        detailDimensions.setText(dimensions);
        detailWeight.setText(weight);
        detailSource.setText(source);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
