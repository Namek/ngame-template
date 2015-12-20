2D Framework/Template
=====================

Libraries:
----------
libgdx (+ libgdx-contrib?) + artemis-odb + artemis-odb-contrib + Universal Tween Engine (+ artemis-odb-orion?)


Starter Tools:
 * app_renamer (gradle conf, xmls, desktoplauncher -> config.title)
 * tools/fix_packages (.gitignored)

Production Tools:
texture packer
gwt xml automatic configurator
packr + download link for openjdk



How to use tools
================

Fixing packages on top of Java files
------------------------------------

(for now it needs adding `tools` into `settings.gradle` file)

`cls&gradlew tools:fixPackages`


Fixing GWT XML definitions
--------------------------

**TODO**



Building sprites (textures)
---------------------------

Go to `work` directory. You'll see 3 subfolders:

* 1_work_stuff - your PSDs or whatever temporary
* 2_to_join - final pictures to join into textures
* 3_out_textures - textures built of previous folder content

Simply put your textures into `2_to_join` and run `pack.bat` which automatically copies compiled sprites into 
`android/assets`.


Packaging
---------

Win32 build with built-in JRE: call `package_desktop.bat` which builds into folder... TODO
