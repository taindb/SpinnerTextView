# TNSpinner
A CustomView like Spinner using Kotlin and TextView

## Demo
<img src="https://i.imgur.com/Cmn1Z7Q.gif" width="200" height="400" />

## Usage

```xml
<vn.taindb.tnspinner.SpinnerTextView
        android:id="@+id/spinner"
        android:layout_width="300dp"
        android:layout_height="@dimen/item_height"
        android:background="@drawable/spinner_item_collapse_bg"
        android:clickable="true"
        android:enabled="true"
        android:hint="[Select a Android Model...]"
        android:textColorHint="#85ffffff"
        android:gravity="center"
        android:textColor="@android:color/white"
        app:arrow_icon="@drawable/ic_arrow"
        app:collapse_background="@drawable/spinner_item_collapse_bg"
        app:dropdown_height="@dimen/item_height"
        app:dropdown_max_height="100dp"
        app:enable_hint_text="true"
        app:expand_background="@drawable/spinner_item_expand_bg"
        app:height_wrap_content="true" />
``` 
