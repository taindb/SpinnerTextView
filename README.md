# TNSpinner
- A CustomView like Spinner using Kotlin and TextView. Not Spinner of Android SDK
- Very easy to custom display item and drop down item from designer or your custom.

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

TODO 
--------
If you want to suggest better implementation or you have found some mistakes in this project, please tell me at [issue](https://github.com/taindb/TNSpinner/issues)


License
--------

    Copyright (C) 2018 Tai Nguyen 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
