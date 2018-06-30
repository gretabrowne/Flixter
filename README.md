# Project 2 - *Flixter*

**Flixter** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **18** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API

The following **optional** features are implemented:

* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)
* [x] Display a nice default [placeholder graphic](https://guides.codepath.com/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [x] Allow user to view details of the movie including ratings and popularity within a separate activity
* [x] Improved the user interface by experimenting with styling and coloring.
* [x] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Glide-Library#transformations)
* [x] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [x] User can navigate up from MovieTrailerActivity to the "home" screen with the full list of movies using an ActionBar back button
* [x] User can view a custom home page app icon designed through [Roman Nurik's](https://romannurik.github.io/AndroidAssetStudio/index.html) app icon design program.
* [x] User can start YouTube trailer from both portrait and landscape mode
* [x] Added getId() function in the Movie class to retrieve stored id value and avoid getting it more than once
* [x] API call for movie trailer filters out non-YouTube videos using the "site" value


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/FXWreZT.gif' title='Flixter Video Walkthrough Horizontal' width='' alt='Video Walkthrough' />

<img src='https://i.imgur.com/bd94r5Y.gif' title='Flixter Video Walkthrough Vertical' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Integrating the YouTube API was definitely the most challenging; for me, I had trouble understanding the difference between the movie ID and the video ID, and this caused me unnecessary difficulty. 
Once I clarified this distinction, it made more sense to me. 
Also, styling the ActionBar was tough, because it requires many additional steps and code to adjust (especially the font). 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2018] [Greta Browne]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
