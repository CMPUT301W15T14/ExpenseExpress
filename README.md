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

If you name your getter methods in the model class match TextViews inside the row layout, it will set those TextViews according to the getter methods in the model class. For example, if getName() returns a String, the TextView with id R.id.name will have that String set to it. If there is no TextView with a matching name then it's ok, and if there is no getter that matches a certain TextView's R.id then it's ignored too.

I don't think we can use it in the final product since on principle, it's slower than writing a custom adapter (since it uses reflection to write the adapter for you), and in practice, it uses some methods from a WTFPL repository (in the import statement) so I don't know if we'd be allowed to use it.

--

-John
