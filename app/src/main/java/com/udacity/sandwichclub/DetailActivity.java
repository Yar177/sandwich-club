package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Sandwich sandwich = new Sandwich();
    TextView alsoKnownAs, placeOfOrigin, description, ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
        int nameSize = sandwich.getAlsoKnownAs().size();
        if(nameSize > 0){
            for (int i = 0; i < nameSize; i++){
                if (i != (nameSize -1)){
                    alsoKnownAs.append(sandwich.getAlsoKnownAs().get(i) + ", ");
                }else {
                    alsoKnownAs.append(sandwich.getAlsoKnownAs().get(i) + ".");
                }
            }
        }
        placeOfOrigin = (TextView) findViewById(R.id.origin_tv);
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        description = (TextView) findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());
        ingredients = (TextView) findViewById(R.id.ingredients_tv);
        int ingredientsize = sandwich.getIngredients().size();
        if(sandwich.getIngredients().size() > 0) {
            for (int i = 0; i < ingredientsize; i++) {
                if (i != (ingredientsize - 1)) {
                    ingredients.append(sandwich.getIngredients().get(i) + ", ");
                } else {
                    ingredients.append(sandwich.getIngredients().get(i) + ".");
                }
            }
        }
    }
}
