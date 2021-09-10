# TechnoRapLoader
 The Image Loader as the name suggests is a powerful image loading library for Android which helps in downloading, caching and displaying images in the ImageView supplied. This can be used in different scenarios depending on the You have to design the same using the following specs. 

Pre-requisites
--------------

* Android Studio 3.6 or later and you know how to use it.

* Make sure Android Studio is updated, as well as your SDK and Gradle.
Otherwise, you may have to wait for a while until all the updates are done.

* A device or emulator that runs API level 16+

You need to be solidly familiar with the Kotlin programming language,
object-oriented design concepts, and Android Development Fundamentals.
In particular:

Getting Started
---------------

1. [Install Android Studio](https://developer.android.com/studio/install.html),
if you don't already have it.
2. Download the sample.
2. Import the sample into Android Studio.
3. Build and run the sample.

<h2>Features</h2>

<ul>
 <li>To be able to asynchronously load the image onto the ImageView</li>
 <li>To be able to load the images faster by caching it in memory. </li>
 <li>To be able to load the images faster by using disk-level caching. </li> 
 <li>To be able to cancel the on-flight load request in case the loading is not needed anymore </li>
</ul>

<h3>How to use TechnoRapLoader </h3>
       
        val imageLoader = TechnoRapLoader.getInstance(this)

        imageLoader.displayImage(URL1,image1 , R.drawable.place_holder)
	
<h3>manage cache </h3>
  
  <h4> setup maximum cache </h4>
  
  
   val cacheSize =4194304 //4MiB
   
   val imageLoader = TechnoRapLoader.getInstance(this  , cacheSize)
   
  <h4> clear cache </h4>
  
   imageLoader.clearcache()
   
   <h4>cancel Loading </h4>
    - to cancel certain image loading task 
   
    imageLoader.cancel(url)
    
  
   -to cancel all tasks
    
    imageLoader.cancelAll()
