import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * TO USE: CHANGE THE PACKAGE NAME (Control+F for "to do" no space)
 *
 * Dig out the getters of the model class and checks them against the
 * Views' ResID's inside row layout and sets the ones with matching names.
 *
 * EXAMPLE:
 * The model class has a field called String name;
 * The model class has a public method called getName():String
 * AND
 * The XML layout has a TextView that is exactly "R.id.name" (lower case n)
 *
 * This will set the TextView with R.id.name to display the String "name"
 *
 * If the getter returns an int and not a String, this will display it properly
 *
 * If the getter returns any other type, then this will ignore the getter/associated field.
 * So if you want to display a GregorianCalendar, convert it to a String with the format you want,
 * in the getter method of the model class.
 *
 * If there is no TextView with a matching R.id to the getter method name, this will ignore the
 * getter/associated field. Eventually, I will add a way to incorporate other Views such as Images.
 *
 * @param <T> The model class
 */
public class EzArrayListAdapter<T> extends BaseAdapter{

    private ArrayList<T> data;
    private LayoutInflater inflater;
    private int lv;
    private Context context;
    private ArrayList<Method> getters;
    private ArrayList<Method> matchedGetters;

    private ArrayList<Method> loadGetters(){
        // Generates an ArrayList containing all the getters of the model class
        T instance = data.get(0);
        Class c = instance.getClass();
        ArrayList<Method> getters = new ArrayList<>();
        getters.addAll(ReflectionUtils.getAllMethods(c,
                ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get")));
        return getters;
    }

    public EzArrayListAdapter(Context context, ArrayList<T> data, String listView){
        this(context, data);
        try {
            //TODO: CHANGE THE PACKAGE NAME
            lv = context.getResources().getIdentifier(listView, "layout", "jz.ironjungle");
        } catch (NullPointerException npe){
            lv = 0;
        }
    }

    public EzArrayListAdapter(Context context, ArrayList<T> data, int listView){
        this(context, data);
        lv = listView;
    }

    /**
     * You can use this constructor if the ListView's ID is exactly R.layout.listView
     */
    private EzArrayListAdapter(Context context, ArrayList<T> data){
        inflater = LayoutInflater.from(context);
        // TODO: CHANGE THE PACKLAGE NAME
        lv = context.getResources().getIdentifier("listView", "layout", "jz.ironjungle");
        this.data = data;
        this.context = context;
        getters = loadGetters();
        matchedGetters = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;


        if (convertView == null){
            view = inflater.inflate(lv, parent, false);
            vh = new ViewHolder();

            // Programmatically generate these -- try to pull these out of the loop later!
            for (int i = 0; i<getters.size(); i++) {
                if (getters.get(i).getReturnType().equals(String.class) ||
                        getters.get(i).getReturnType().equals(int.class)) {
                    String getterName = getters.get(i).getName();
                    String fieldName = getFieldNameFromGetterName(getterName);
                    int resId = getResIdFromFieldName(fieldName);
                    if (view.findViewById(resId) != null) {
                        vh.textViews.add((TextView) view.findViewById(resId));
                        if (!(matchedGetters.contains(getters.get(i)))) {
                            matchedGetters.add(getters.get(i));
                        }
                    }
                }
            }
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }

        T datum = data.get(position);
        // Sets the TextViews
        for (int i = 0; i<matchedGetters.size(); i++){
            // if it's a number then convert to String first
            if (matchedGetters.get(i).getReturnType().equals(int.class)){
                String numString;
                try {
                    Integer num = (int) matchedGetters.get(i).invoke(datum);
                    numString = num.toString();
                    vh.textViews.get(i).setText(numString);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else { // if not a number then it's a string. set the text normally
                try {
                    vh.textViews.get(i).setText((CharSequence) matchedGetters.get(i).invoke(datum));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return view;
    }

    private String getFieldNameFromGetterName(String getterName) {
        // cut off "get"
        String fieldName = getterName.substring(3);
        // lowercase the first letter so it matches the field in the model class
        String firstLetter = fieldName.substring(0,1);
        String otherLetters = fieldName.substring(1);
        return firstLetter.toLowerCase()+otherLetters;
    }

    private int getResIdFromFieldName(String fieldName) {
        int id;

        // get the resId -- TODO: CHANGE THE PACKAGE NAME
        try { id = context.getResources().getIdentifier(fieldName, "id", "jz.ironjungle"); }
            catch (NullPointerException npe) {
                return 0;
            }
        return id;
        }

    class ViewHolder{
        public ArrayList<TextView> textViews;

        public ViewHolder(){
            textViews = new ArrayList<>();
        }
    }
}
