package info.hxq.palettedemo;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private final ArrayList<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewList.removeAll(viewList);
        viewList.add(findViewById(R.id.tv1));
        viewList.add(findViewById(R.id.tv2));
        viewList.add(findViewById(R.id.tv3));
        viewList.add(findViewById(R.id.tv4));
        viewList.add(findViewById(R.id.tv5));
        viewList.add(findViewById(R.id.tv6));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strictdroid);
        Palette.generateAsync(bitmap, new PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                getSupportActionBar().setBackgroundDrawable(
                        new ColorDrawable(palette.getVibrantSwatch().getRgb()));
                for (int i = 0; i < viewList.size(); i++) {
                    View view = viewList.get(i);
                    switch (i) {
                        case 0:
                            view.setBackgroundColor(palette.getDarkMutedColor(Color.BLACK));
                            break;
                        case 1:
                            view.setBackgroundColor(palette.getDarkVibrantColor(Color.BLACK));
                            break;
                        case 2:
                            view.setBackgroundColor(palette.getLightMutedColor(Color.BLACK));
                            break;
                        case 3:
                            view.setBackgroundColor(palette.getLightVibrantColor(Color.BLACK));
                            break;
                        case 4:
                            view.setBackgroundColor(palette.getMutedColor(Color.BLACK));
                            break;
                        case 5:
                            view.setBackgroundColor(palette.getVibrantColor(Color.BLACK));
                            break;
                    }

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
