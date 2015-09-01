/*
 * Author: Pablo Angel Veliz
 *
 * http://about.me/paveliz
 *
 * https://ar.linkedin.com/in/pabloveliz
 *
 */

package examples.paveliz.com.twofragmentsexamplepart2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

    private static final Integer PORTRAIT_LAYOUT_MODE   = 1;
    private static final Integer LANDSCAPE_LAYOUT_MODE  = 2;

    private OnItemSelectedFromTheList mItemListener;

    private Integer layoutMode = PORTRAIT_LAYOUT_MODE;
    private Integer mPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            layoutMode = arguments.getInt("LayoutMode");
        }

        if(savedInstanceState!=null){
            mPosition = savedInstanceState.getInt("position");
        }

        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView mylistview = (ListView) rootView.findViewById(R.id.listView);

        String[] alumnos = getResources().getStringArray(R.array.alumnos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, alumnos);

        mylistview.setAdapter(adapter);

        if(mPosition >= 0)
            mylistview.setSelection(mPosition);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Calling What To Do in the MainActivity.

                mItemListener.OnItemSelectedFromTheList(position);

            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("position", mPosition);
    }

    /**
     * Why I'm doing this?
     *
     * See the explanation in the OnItemSelectedFromTheList Interface.
     *
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mItemListener = (OnItemSelectedFromTheList) activity;

        } catch (ClassCastException castException) {
            /**
             * If this error rises, means that the Activity that is using
             * this fragment doesn't have a OnItemSelectedFromTheList
             * implementation.
             */
        }
    }

}
