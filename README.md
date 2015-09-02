When I started to study fragments on Android, I discover the following example on the Developer Android Site: http://developer.android.com/guide/components/fragments.html

Basically, 1 Activity that change two fragments according the layout. It's sounds very useful but this documentation lacks of code that can help me to understand how to create this kind of layouts and fragment interaction.

This is because I created this small example focused on solve the two fragments interaction with 1 main layout.

1) Layouts

I created two activity_main.xml layouts, one in the "layout" folder (default) and the second one in the "layout_land" folder. Android will use this layout on screen rotation to landscape.

activity_main.xml (portrait)

The main layout in portrait mode, will only include the main fragment directly.

activity_main.xml (landscape)

The main layout in landscape mode, will include the main fragment and a second framelayout that I will use as a "container" for the second fragment that will be added programmatically. 

Notice that this layout use a LinearLayout with an Horizontal Divider and of course, Horizontal orientation:

android:divider="?android:dividerHorizontal"
android:orientation="horizontal"

Also, notice that I'm using the "android:layout_weight" property according the Android Developer documentation: http://developer.android.com/guide/topics/ui/layout/linear.html#Weight

2) Second fragment detection

Because the main activity will use the both activity_main.xml according the layout orientation I need to find a way to detect if I'm using the layout with one fragment or the layout with two fragments, because if not..., well, nothing it's going to work well.

To do this, I basically asking with the following line if the second framework exists or not:

if (findViewById(R.id.fragment_detail)!=null)

If this conditions its "True", it's because I'm in landscape mode and I'm using the Two Fragments Layout so I need to call the second fragment and show it. If not, there is nothing to worry, because I'm using the one fragment layout that it's already included and called in the layout.

As a result, the main activity will add the second fragment only when he detect need to do it.

3) Parameters

But I also need that the main fragment knows when it's called in landscape or portrait. Because it it's always loaded, I'm using the findFragmentById to find it and pass a layout parameter predefined by me.

fragmentList = ((ListFragment)getSupportFragmentManager()
        .findFragmentById(R.id.fragment_list));

fragmentList.setLayoutMode(layoutMode);

With this parameter, the setOnItemClickListener event now knows if it needs to 

4) Fragment calls a method in the Activity (NO hardcoding)

I need to find a way to manage to manage the OnItemClickListener from the ListView in the DetailFragment but calling a method in the MainActivity.

Basically:

a) I don't want to put references or calls for Activities inside the OnItemClickListener event in the DetailFragment. It supposed that fragments need to be "reusable", so you should not put references to external activities inside.

b) I want to call an independent method created in the MainActivity from the OnItemClickListener event in the DetailFragment but, I don't want to create a "hardcoding" relationship between both parts. Because again..., I don't know who is going to reuse this fragment in the feature calling it from another activity!

The Result:

- I create an Interface with a method.
- I implemented this method in the MainActivity.
- I got an instance of the Activity that creates the DetailFragment in the "onAttach" event inside the DetailFragment.
- I used the instance to access the method that then is called in the ListView.

This is a "clear" way to call a method from a Fragment to an Activity without compromise the independence of the fragment.

As a result, I created what I understand it's a very simple example that covers:

- Managing different layouts between landscape and portrait.
- Implementing and reusing fragments between layouts.
- Uncouple methods calling between fragments and activities.

Main Layout in Portrait:

![Portrait Layout](https://dl.dropboxusercontent.com/u/5130730/Github/layout_main1.PNG)

![Portrait Layout](https://dl.dropboxusercontent.com/u/5130730/Github/layout_main2.PNG)

Main Layout in Landscape:

![Portrait Layout](https://dl.dropboxusercontent.com/u/5130730/Github/layout_main3.PNG)


Hope it helps you to understand this concepts. But if you find any doubts or if you find any issue or suggestions to improve this example, please, don't hesitate to contact me.

-----------------------------------------
Pablo Angel Veliz
https://ar.linkedin.com/in/pabloveliz
