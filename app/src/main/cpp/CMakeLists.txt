# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(distribution_DIR ${PROJECT_SOURCE_DIR}../../jniLibs)
include_directories(src/main/cpp/inc)

add_library( fmod
        SHARED
        IMPORTED )
set_target_properties( fmod
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libfmod.so)


add_library( fmodL
        SHARED
        IMPORTED )
set_target_properties( fmodL
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so )


#---------------------------------------------------------------
#       变声[萝莉][大叔][搞怪][惊悚][空灵]
#---------------------------------------------------------------
#find_library( # Sets the name of the path variable.
#        log-lib
#        # Specifies the name of the NDK library that
#        # you want CMake to locate.
#        log)
#add_library( effect
#        SHARED
#        effect.cpp)
#
#target_link_libraries( effect
#        fmod
#        fmodL
#        ${log-lib} )
#---------------------------------------------------------------

# effects.cpp play_sound.cpp convolution_reverb.cpp 每个文件代表一种模式，主方法名都是FMOD_Main，由common_platform.cpp的main调用
add_library( effect #偷懒了，不想重新取名了，否则在java代码中System.loadLibrary也要同步改名
        SHARED
        fmod/net_stream.cpp #gapless_playback.cpp #convolution_reverb.cpp
        fmod/common.cpp
        fmod/common_platform.cpp
        )
find_library( # Sets the name of the path variable.
        log-lib
        log)
target_link_libraries( effect #偷懒了，不想重新取名了，否则在java代码中System.loadLibrary也要同步改名
        fmod
        fmodL
        ${log-lib} )
#---------------------------------------------------------------
#add_library( effect
#        SHARED
#        fmod/effects.cpp
#        fmod/common.cpp
#        fmod/common_platform.cpp
#        )
#find_library( # Sets the name of the path variable.
#        log-lib
#        log)
#target_link_libraries( effect
#        fmod
#        fmodL
#        ${log-lib} )
#---------------------------------------------------------------
#add_library( sound
#        SHARED
#        fmod/play_sound.cpp
#        fmod/common.cpp
#        fmod/common_platform.cpp)
#find_library( # Sets the name of the path variable.
#        log-lib
#        log)
#target_link_libraries( sound
#        fmod
#        fmodL
#        ${log-lib} )



