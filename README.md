Hey everyone if anyone wants to get started with the coding, I wrote some code for debugging:

--

EzToast and EzLog, interfaces, implement them and copy paste code from the java doc, to save typing when you
try to Toast or Log.d.

In addition, these all work:

-toast(String);

-toast(int); (or any Number)

-toast(String,int,String,int, etc...); will automatically add spaces between comma-separated things

-log(String);

-log(); produces a "." on its own line.

-log(int); (or any Number)

-log(String,int,String,int, etc ...); will automatically add spaces between commma-separated things

--

EzArrayListAdapter class.

Purpose: Easily, quickly test ListView UI visually.

Instantiate this with 3 parameters:

1. context

2. ArrayList holding the data model class elements you want to display in a listview (e.g. ArrayList<ClaimModel>)

3. the ListView ROW LAYOUT R.id (R.layout.listview_row) -- NOT the ListView's R.id inside the parent XML

Example:

EzArrayListAdapter adapter = new EzArrayListAdapter(this, claims, R.layout.claimslist_row);

After that you would just go listview.set(adapter); like normal.

If the model class's getter method names match TextViews inside the row layout, it will set those TextViews according to the getter methods in the model class. For example, if getName() returns a String, the TextView with id R.id.name will have that String set to it. (note lower case n in the R.id, so it matches the object field).

If there is no TextView with a matching name then the getter is skipped, and if there is no getter that matches a certain TextView's R.id then the TextView is skipped.

Theoretically it has slower performance than coding a separate, custom ArrayAdapter/BaseAdapter for each ListView since it uses reflection. No practical difference. Only thing is I don't know if we can use the WTFPL methods (https://github.com/ronmamo/reflections) in our project.

--

-John
