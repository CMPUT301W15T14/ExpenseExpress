Hey everyone if anyone wants to get started with the coding, I wrote some code for debugging:

--

Toas.java and Lo.java classes, if they're imported then you can save some typing when doing debugging.
Toas.t makes a toast, and Lo.g makes a log.

-Toas.t(String);

-Toas.t(int); (or any Number)

-Toas.t(String,int,String,int, etc...); will automatically add spaces between comma-separated things

-Lo.g(String);

-Lo.g(); produces a "." on its own line.

-Lo.g(int); (or any Number)

-Lo.g(String,int,String,int, etc ...); will automatically add spaces between commma-separated things

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

Response to above:

Ill try to push the UI by thursday; Its all using relative because I was just taking images of them so im going to switch it all to linear then push them.
Zach
