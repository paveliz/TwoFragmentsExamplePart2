/*
 * Author: Pablo Angel Veliz
 *
 * http://about.me/paveliz
 *
 * https://ar.linkedin.com/in/pabloveliz
 *
 */

package examples.paveliz.com.twofragmentsexamplepart2;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnItemSelectedFromTheList {

    private static final Integer PORTRAIT_LAYOUT_MODE   = 1;
    private static final Integer LANDSCAPE_LAYOUT_MODE  = 2;

    private Integer layoutMode = PORTRAIT_LAYOUT_MODE;

    DetailFragment fragmentDetail;
    ListFragment fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * First, I'm going to validate if we are using the
         * layout for landscape option under "layout-land"
         * folder or not.
         *
         * Because if it's the case, I'm going to show the
         * two fragments at the same time.
         *
         */

        if (findViewById(R.id.fragment_detail)!=null) {

            layoutMode = LANDSCAPE_LAYOUT_MODE;

            if (savedInstanceState == null) {

                fragmentDetail = new DetailFragment();

                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();

                transaction.add(R.id.fragment_detail, fragmentDetail, "frag_detail");
                transaction.commit();
            }
        }

    }

    /**
     * This method will be called from the setOnItemClickListener
     * on the ListFragment.
     */
    @Override
    public void OnItemSelectedFromTheList(Integer mPosition) {

        /**
         * First, I need to detect in which mode Iam, Landscape or Portrait,
         * because in the Portrait mode I don't have access to the second
         * fragment (Detail), so I'm going to call the Second Activity
         * with an Intent and pass the parameters.
         *
         * If I'm in the Landscape mode it means that two fragments are
         * in the same activity, so I'm going to pass the position as
         * a parameter to the second fragment and I'm going to use
         * the "replace" to update the fragment.
         *
         */

        Bundle args = new Bundle();
        args.putInt("Indice", mPosition);

        if (layoutMode == LANDSCAPE_LAYOUT_MODE) {

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail, fragment)
                    .commit();
        } else {

            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra("args", args);
            startActivity(i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
