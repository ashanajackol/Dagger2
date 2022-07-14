# Dagger2
Implementing dagger 2

on this describe only the steps to viewmodel injection on dagger, it's fucking hard (use hilt)

Dagger 2 Viewmodel injection
----------------------------
step 1 -> create a ViewModelProvidersFactory.class
[this map your viewmodels with key value pairs]

step 2 -> create ViewModelFactoryModule.class
[this provides the viewmodel factory]

step 3 -> create ViewModelKeys.class
[I have no idea what is the purpose of this fucking class]

step 4 -> create AuthViewModelModule.class
[you need to create separate classes based on how many viewmodel classes you are using.
As a example, if you have MainViewModel, you have to create MainViewModelModule.class.
this is where you add youe viewmodel key]

step 5 -> Go to activity/fragment and inject the Factory and initialize viewmodel
