package examples.paveliz.com.twofragmentsexamplepart2;

/**
 * I created this interface only to manage the OnItemClickListener
 * from the ListView in the DetailFragment but calling a method in
 * the MainActivity.
 *
 * Basically:
 *
 * 1) I don't want to put references or calls for Activities inside the
 *    OnItemClickListener event in the DetailFragment. It supposed that
 *    fragments need to be "reusables", so you should not put references
 *    to external activities inside.
 *
 * 2) I want to call an independent method created in the MainActivity
 *    from the OnItemClickListener event in the DetailFragment but, I don't
 *    want to create a "hardcoding" relationship between both parts.
 *    Because again..., I don't know who is going to reuse this fragment
 *    in the feature calling it from another activity!
 *
 * The Result:
 *
 * - I create an Interface with a method.
 * - I Implemented this method in the MainActivity.
 * - I got an instance of the Activity that creates the DetailFragment
 *   in the "onAttach" event of it.
 * - I used the instance to access the method that then is called in the
 *   ListView.
 *
 * This is a "clear" way to call a method from a Fragment to an Activity
 * without compromise the independence of the fragment.
 *
 */

public interface OnItemSelectedFromTheList {
    public void OnItemSelectedFromTheList(Integer mPosition);

}
