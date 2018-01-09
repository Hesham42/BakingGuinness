udacity Documenation
hmostafa3bd
January 2018
1
Introduction
Abstract
in this project we using some edited Feature like
1)fragment 2)multiy Screen size 3)widget 4)testeing Gui
2
Pojo Page
when I thing how can me start this project first i’m used postman to show the
type of data form that
link: DataJSON
and see the data we have here JsonArray have in the element JsonObject exmple:
Recipe
private Integer id;
private String name;
private List<Ingredient>ingredients = null;
private List<Step>steps = null;
private Integer servings;
private String image;
Steps
private Integer id;
private String shortDescription;
private String description;
private String videoURL;
private String thumbnailURL;
Ingredient
private Double quantity;
private String measure;
private String ingredient;
and i’m used the Pojo site in the link : Pojo site
for make easy class for your data that is the Pojo of My data and use Parcelable,
Serializable in
github : Pojo Page
1Figure 1: Tablet
Figure 2: Tablet
2Figure 3: row element
3
Service Page
using Retrofit:make client and server classes for connection the Link: Server-
Class
the Link: Client Class
the Comm Class
the server Class have function to get data
form the Client Class have the retrofit Code
that is used to get data and I need now Comm Class to send the response to
the main to use it and I implement Comm Class in Main to used “in this I’m
ready to call function [getRecipes()] and retrieve All Recipes”
4
4.1
Gui
MainLayout
I used the 2 of size of screen Large size for tablet and the phone size and put
android:tag=”phone” For the Phone
AND put for android:tag=”tablet” the Tablet for the Large screen in the Re-
cyclerView to know the different and make row of element as img:
figure 1 and 2
Link:activity main layout
And start for design row elment in RecyelerView
link:row recipes layout
34.2
Details Activity
Have the same 2 size screen the first screen for the Tablet and the second Screen
for phone The phone screen :
Have One FrameLayout For the Details :
Link:activity recipe details Layout
the Tablet Screen :
Have 2 FrameLayout the Frist FrameLayout id = fragmentone to display the
Ingredients
Steps the Second FrameLayout id=fragmenttwo have the Contain mediaplayer
and description for Steps
Now we start a row elements in all of them
first we Start we have 2 Recycler View in the layout one for Steps second for
ingredient.
The first Recycler View to ingredient is scroll Horizontally :
second Recycler View to steps is Scroll Vertically :
4.3
Details Activity
Have the same 2 size screen the first screen for the Tablet and the second Screen
for phone The phone screen :
Have One FrameLayout For the Details :
Link:activity recipe details Layout
the Tablet Screen :
Have 2 FrameLayout the Frist FrameLayout id = fragmentone to display the
Ingredients
Steps the Second FrameLayout id=fragmenttwo have the Contain mediaplayer
and description for Steps
Now we start a row elements in all of them
first we Start we have 2 Recycler View in the layout one for Steps second for
ingredient.
The first Recycler View to ingredient is scroll Horizontally :
second Recycler View to steps is Scroll Vertically :
that is the link of the Layout name
that is the link of the Layout name
4.3.1
Step Fragment
link:step fragment layout
I will make row for every Recycler View
4.3.2
Row for Ingredient
the link:row ingredient layout
44.3.3
Row for steps
thelink :row steps layout
and if we in the scale phone make the
4.3.4
activity step
activity step details layout
if we scale large for table the second part of FrameLayout id=fragmenttwo vedio
details fragment Layout
and we usying media player on it that is
<com.google.android.exoplayer2.ui.SimpleExoPlayerView
android:id=” a +id/playerView”
android:layout w idth = ”match p arent”
android : layout h eight = ”match p arent”
and
wedesignthatandseparatethedesignf or2partthef irstF ramelayoutisaboutmediaplayer
thesecoundF ramelayoutisf or2Buttonf ornextorpreviousstepsanddescriptionof steps
4.3.5
vedio details fragment layout
vedio details fragment Layout
5
Adapter
we have 3 adapter for
1)RecipesAdapter
2) IngredientsAdapter
3) StepsAdapter
link of them :
RecipesAdapter for the row recipes layout and for every element in the Recy-
clerView of Recipes
put Name , StepsSize ,Serve . IngredientsAdapter
for the row ingredient layout
and for every element in RecyclerView of Ingredients
put Name,quantity,Measure StepsAdapter
for the row steps layout and for every element in RecyclerView of
Steps
put Title.
6
Utilites
we have 2 Utilites Class first ”RecyclerTouchListener”
for the Recycler view Touch and we will used It if you need to Click on element
5in Recycler view
the other class was Interface ”FragmentOneListener”
this have 2 function first
void setStep(int index, ArrayList<Step>steps);
this setStep send the index and ArrayList between activity and fragment
void setCurrent(int index);
this for send index from fragment to Activity
7
7.1
Fragment
StepFragment
we have link:StepFragment
this fragment was control in Gui name step fragment layout
we Using in the StepFragment ButterKnife for intialize the element in GUI
and we make Obj form Interface class in Utitlies
we have state of the fragment
if state =null
we get ingredients , steps from the parceble from the activity
set Tablet = false
else
we get all the save state as below
1)ingredients,steps,tablet,index,and p1 ,p2
the last we used the Utlitle Class RecyclerTouchListener for Click
and if you click we will send the index and the steps to the RecipeDetialsActiv-
ity
FragmentOneListener listener;
we have initialize this by set function in the RecipeDetialsActivity
for send the Postion and Steps to the RecipeDetialsActivity if you Click on the
Recyclerview
LinearLayoutManager ingredientsManager,stepsManager;
that we intialize to tell the Recycleview if horzental for ingredients
and for steps vertical
ArrayList<Step>steps;
ArrayList<Ingredient>ingredients;
we intalize this to contain the data for Steps or Ingradients
boolean tablet;
for chick if Tablet will be True we will if Phone will be False
6int[]trackers;
int index;
tracker we initialize the same size of Steps list if tablet==true we will initialize
to 1 because to show the first element in the fragment
and we will send with Adapter to change color if the position =1 else will
give them another color
Index we initialize if the state = null index=0 because that is the first time to
enter in the fragment else we will get from state the last potion we using in the
UpdateView(index) to make recycle view in the right potion int p1, p2;
this 2 element initialize to get the first completely element I every RecycleView
and we will check if they are not= 0 we set the position to the right
p1 for ingredient Recycler view and p2 for steps Recycler view
we have many function we will description them
public void updateView(int index)
this function using to Update gui
we first get the index and check if the Tablet we return
or if we phone the trackers get steps size
and initialize by index we will set the trackers of adapter to the same trackers
and we
recycler.getAdapter().notifyDataSetChanged();
recycler.scrollToPosition(index);
we make update to the the gui and sroll to the index
onSaveInstanceState(Bundle outState)
we save state ingredient ,steps ,tablet Boolean ,index position
and p1 to the first completeyVisibleItemPosition for in ingredient
and p2 to the 2 for completeyVisibleItemPosition for steps
7.2
VedioFragment
link:VedioFragment first this fragment was using to Control in
vedio details fragment Layout
we using the same ButterKnife to control the Gui from id
we implements View.onClickListener to click Button next or back
a BindView(R.id.description)
TextView description;
a BindView(R.id.currentStep)
TextView current;
a BindView(R.id.next b utton)
F loatingActionButtonnext;
a BindV iew(R.id.back b utton)
F loatingActionButtonback;
a BindV iew(R.id.playerV iew)
SimpleExoP layerV iewplayerV iew;
a BindV iew(R.id.empty)
7T extV iewempty;
a BindV iew(R.id.root)
LinearLayoutlayout;
a BindV iew(R.id.f 1)
F rameLayoutf 1;
a BindV iew(R.id.f 2)
F rameLayoutf 2;
a BindV iew(R.id.image)
ImageV iewimageV iew;
Andinitialize
F ragmentOneListenerlistener;
and make setFragmentListener(FragmentOneListener listener) function to ini-
tialize that variable in the
if that intialize in Activity we will send the index
listener.setCurrent(currentIndex);
ArrayList<Step>steps;
initialize this variable to get all steps on it
Boolean tablet;
this Boolean Value to know if the Tablet or phone size of screen
it’s take about state of this fragment if we save state we will come some variable
1)steps,CurrentIndex,tablet
we will get from state we save in function Saveinstancestate()
else we get the data from sender Activity or fragment
1)step,CurrentIndex,tablet we make function Show();
this function control the Gui on It
it’s start at first Check if Currentindex <=0 to make back button Gone else we
make it visible
and we check if the listener is intialze not null we will send the currentindex by
listener.setCurrent(currentIndex);
and check if more than size of steps we make next button Gone else we make it
visible
then must to releasePlayer();
this function will use to rellease the mediaplaye by Check if player !=null then
make release to player
and initialize player to null
that make to check if the player run on not
then check if steps in the currentindex the Vedio url is empty and steps in the
current index imgeUrl is empty then we
playerViewer will set show Gone , imageView Gone and empty text Visible
else check if steps in current index Video URL is not empty then We get Video
Url and set in function to initialize the player
initializePlayer(Uri.parse(videoUrl));
that function to initialize the player set on it media player Url
and we using MediaSource Class to make obj on it we must use to initialize this
8object to function buildMediaSource(Uri uri)
and when praper set mediasouce and resetpotion =ture and resetState =false
else we will get imgeUrl and set into imgeview we using Glide lib and make
video Gone and Playerview is Gone
and we get descrption will put text and we put number of steps on the Current
and the end of function we will hideSystemUi(); we used to make visible of all
imgeview,empty,playerview
we now take about on function about orientation
onConfigurationChanged(final Configuration newConfig)
we using it to save the same Gui is true not destroy we initialize 2 variable one
for width second for Height and set 2 fragment f1 and f2
and we usign hideSystemUi(); and check if table return
when we implement View.onClickListener we have override method
onClick(View v) the we if button next we will size of step -1 to currentindex +1
then call function show(); to initialize the change
or
back button then if currentindex =0 will return else we currentindex -1 and call
function show(); to initialize the change
we have onStart() Override function we using it
we check if tablet return
and
we check orientation =1 that mean PORTRAIT we get width and height to
initialize the valve we are make else we get the width and hight and set to frag-
ment f1 and f2 the width and the hight
8
8.1
Activity:
the Main Activity
we have link here:Main Activity we first implement Interface class Comm to get
Respons from 2
Function onRespons(Resopns<List<Recipes>>response);
this function get response from Client if the respond Success and we used this
function to set Variable in recipes and we make another Function Called ren-
derRecyclerView();
that for take if the Tablet we using GridLayout or Phone to using LinearLayout
or
Function onFailure(String message);
if the response Failure we print message “No internet connection ” and make
Button Visible to make reload and calling the same function in Client.get()
description first we make initialize ArrayList<Recipe>recipes for take all Recipe
come form Respond and using ButterKnife Lib to help Us to Initialize the GUI
and Like a BindView(R.id.recipesList)
9RecyclerView recyclerView;
a BindView(R.id.progressBar)
ProgressBar progressbar;
a BindView(R.id.reload)
And we Check if the state is not Null that mean he is the first time in this
Activity and not save and Object
and we get the Function form the Client Class Client.getRecipes(this);
and send the Activity to the Listner
And Using Espresso UI Testing
private CountingIdlingResource mIdlingResource = new CountingIdlingResource(”Loading D ata”);
mIdlingResource.increment();
bef oretheCallingT heApiP arsingand
mIdlingResource.decrement();
Af tertheT heGU Iinf unctionrenderRecyclerV iew();
and have Function is OnSaveInstanceState(Bundle outstate);
if Tablet save the position we are on it
if Phone save the Position we are on it
and have Function is OnRestoreInstanceState(Bundel)
we restore the 1)recipes 2)potion 3)we called function to create Gui renderRecy-
clerView();
we Make Button reload that make reload to Api Parsing to get respons .
And when you Click on the Recycler view you will send to the RecipeDetails-
Activity steps List and Ingredient List and the Name for the Title
8.2
RecipeDetialsActivity
Link:RecipeDetialsActivity implement FragmentOneListener to getIndex and
steps using setStep(int index, ArrayList<Step>steps)
and get only index by setCurrent(int index) form fragment
first initialize some variable
1)FrameLayout framelayout get frame from
2)Boolean Tablet from to set tablet true if true
3)VedioFragment and StepFragment
4) ArrayList<Step;
5)String name for title
we have 2 state in the if state == null initialize the stepfragment,stepfragment.setFragmentListener(this)
that for that about to get data from fragment to be initialize and set fragmen-
tOne
if framelayout==null
this mean tablet =false
else setStep(0,steps)
else
10we getstate and put into fragment
and initialize the same listener of fragment and Check if stepfragme if not added
we initialize
and check if the vediofragment is not null we will set the fragment
and when we implement the interface class we have 2 function will enter to
them
setStep(int index ,ArrayList<Step):
we check if tablet we will we using Video fragment if start initialize it and start
to use it
if Phone screen we will send data to the StepDetailsActivity
the data is sending it 1)steps,Currentindex,name
the SetCurrent(int index) if tablet make updateView for step fragment
now want to save the state we have 2 size screen and 2 fragment
we will save stepFragmentgetFragmentManager()
.putFragment(outState, getResources().getString(R.string.main), stepFragment);
but
if tablet and the fragment not null that mean is that tablet screen we will
save also video fragment by
getFragmentManager()
.putFragment(outState,
getResources().getString(R.string.detail),
vedioFragment);
in onResume() we ill set tablet false if the framelayout null
8.3
StepDetailsActivity
the last Activity link:https://github.com/Hesham42/BakingGuinness/blob/
master/app/src/main/java/com/example/root/bakingapp/Activity/StepDetailsActivity.
java
First we start initialize VedioFragment
and set fragment in the activity
and we want save state of this by function onSaveInstanceState(Bundle out-
State)
and
getFragmentManager()
.putFragment(outState,getResources().getString(R.string.fragment)
, stepFragment);
and restore it by
onRestoreInstanceState(Bundle savedInstanceState)
stepFragment = (VedioFragment) getFragmentManager()
.getFragment(savedInstanceState
,getResources().getString(R.string.fragment));
and chick if added this fragment we will return else we will added
119
Widget
first must save this data on database because don’t disappear if me close app
or offline list start with
9.1
Database
we want save some variable we make Class for Contract : link:Contract we will
save all Contract Column
Class for Database :
link:Database that start to make Context Variable to get the context that used
on
It the make Constructor for enter the same Context we used on it
then in onCreate(SQLiteDatabase db)
we Create 2 table one for recipe ,Ingredient
db.execSQL
(”CREATE TABLE recipe(id INTEGER PRIMARY KEY AUTOINCREMENT
,
name TEXT ,
widget i dIN T EGER)”);
db.execSQL
(”CREAT ET ABLEingredient(idIN T EGERP RIM ARY KEY AU T OIN CREM EN T,
contentT EXT,
measureT EXT,
quantityREAL,
recipe i dIN T EGER)”);
Have onConfigure(SQLiteDatabase db)
to set for setForeignKeyConstraintsEnabled
insertItem(WidgetModel model, int widgetId)
we make 2 ContentValue first for Recipes second for ingredient
and insert into table Recipes title and widgetId
into table all ingredient second content
Function getRecipeTitle(int widgetId)
for get recipe Title by widgetId in using querey
“”SELECT * FROM recipe WHERE widget i d = ” + widgetId”
F unctiongetIngredients(intwidgetId)
f orgetIngredientsbyQuere
”SELECT content,
measure,
quantity
F ROM
ingredientjoinrecipeoningredient.recipe i d = recipe.id
W HERE
widget i d = ” + widgetId”
12the class WidgetModel the link :WidgetModel public String recipeTitle;
public ArrayList<Ingredient>ingredients;and constructor to initialize this Vari-
able
9.2
widgetGUiandClasses
frist we start to make the Gui of Widget and we want to customize the Widget
we create first
9.2.1
recpe wigget layout
Link:recipe widget layout
this we make Titile of Recipes and ListView for ingredient
we need to put row of list view we make
and we go to the XML Page and but on it this Configuration layout by this
9.2.2
widget list item layout
link:widget l ist i tem we put on it 2 textview first for name secound for measure
now we need to Configuration between the widget
and the customize we make Customize selection for Recipes
we make first textView of Choose and spinner to make us Choose
and Button of okay
we named It recipe w idget c onf igurelink : recipe w idget c onf igure
nowwef inishtheGU Iof W idgetLetsstartcode
9.2.3
RecipeWidget
RecipeWidget this class have Multiple Function
1) updateAppWidget(Context context,
AppWidgetManager appWidgetManager,
int appWidgetId)
in first we inilize Database for the same Context
and Title for
the Recipes get from database database.getRecipeTitle(appWidgetId);
then Catch Viwe is layout recipe w idgetandsettitleof theV iew
andwillcreateIntenttogotoW idgetService
andsendW idgetIdf orthisclassf orgetingredient
andChangeV iewif dataChangedandsetdatainListV iewthisClasslikeAdapter
thenwesetV iewof listbythisintentwemakeAppIntent
andit 0 sM ainActivtiyandpendingthiswithlistview
2)putf unctionnumber(1)intoonU pdatetousingit
139.2.4
RecipeWidgetConfigureActivity
link:RecipeWidgetConfigureActivity
and we must set this into the XML page
android:configure=”com.example.root.bakingapp.widget.RecipeWidgetConfigureActivity”
first initialize Spinner and
and ArrayList<Recipe>recipes
and int mAppWidgetId for id
in onCreate() setLayout
the initialize spinner by id and set Button on Click
findViewById(R.id.add b utton)
.setOnClickListener(mOnClickListener); andwe
f indwidgetf romidf omtheintentcreateintentandgetidsetonmAppwidgetIda
wecheckif thisactivitywasstartedwithanintent
withoutappwigdetidf inishwithanerror
andsettheClient.getRecipes(this);
togettheresponsin
onResponse(Response<List<Recipe>>response)
wegetallnameof RecipesandsetintoSpinner
whenweClickintotheButtonwehaveweCreateObjf ormW idgetM odleclass
andsetthenameorReccipes
andArraylist<Ingredient>f ormthesameposition
andinsertthismodelintoDatabaseBymAppwidgetId
theupdateChangeByAppW idgetM anger
10
UiTesting
in ui testing we using this class RecyclerViewMatcher
for easy adapter for thesting recycleview the we have many function that make
easy testing like
atPositionOnView(final int position, final int targetViewId)
this take the posting and the id of the check to know is match or not and return
match view
the class of testing is
10.1
TestApp
the link: TestApp in this class we set the Rule like this @Rule
public ActivityTestRule¡ com.example.root.bakingapp.Activity.MainActivity¿
mActivityRule
= new ActivityTestRule¡¿
(com.example.root.bakingapp.Activity.MainActivity.class);
14and set 2 function Before run and After @Before
public void registerIdlingResource()
mIdlingResource =
mActivityRule.getActivity().getIdlingResource();
// To prove that the test fails, omit this call:
Espresso.registerIdlingResources(mIdlingResource);
@After
public void unregisterIdlingResource()
if (mIdlingResource != null)
Espresso.unregisterIdlingResources(mIdlingResource);
in this we check if the first elemnt is =
public void testingRecyclerView()
in first element in recyclerview using
recipe s teps c ount = 7
R.id.recipe n ame = ”N utellaP ie”
recipe s ervings = 8
RecyclerViewMatcher
when Click on it for ingredientList and stepsList
15
