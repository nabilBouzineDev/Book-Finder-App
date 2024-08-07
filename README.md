<p align="center">
  <img src="/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png">
  <h1 align="center">BookFinder</h1>
  <p  align="center"> A native android app that allows users to search for books using the <a href="https://developers.google.com/books/docs/overview">Google Book Api</a>. The app is built with Kotlin and leverages various modern Android development libraries and techniques.<br></p>
</p>

# Key Components
> [!NOTE]
>
> - **Retrofit and Coil**: Load and display data efficiently.
> - **Kotlin Coroutines**: Handle asynchronous tasks and manage concurrency.
> - **Type Safe Navigation**: Implement the new type-safe navigation component.
> - **Shared Elements Transitions**: Use the new shared elements transitions for smooth animations between screens.
> - **Manual Dependency Injection (DI)**: Implement manual DI along with the repository pattern for better code management.
> - **Pagination for Infinite Scroll**: Usage of Pagin3 library to fetch data progressively from the Google Books API for an infinite scrolling experience.
> - **Custom App Icon and Splash Screen**: Enhance the user experience with a custom app icon and a splash screen.
> - **Lottie Animations**: Enhance user interactions and flow with engaging Lottie animations.

# Prerequisites
> [!IMPORTANT]
> 
> It's recommended to have these prerequisites for completing this project:
> - Ability to create and run a project in Android Studio.
> - Ability to create layouts in Jetpack Compose.
> - Experience using coroutines in Kotlin.
> - Experience working with Retrofit, Coil, and Kotlin Serialization.

# Showcase

  ## Demo
  > [!TIP]
  > ![book_app_demo](https://github.com/user-attachments/assets/8d752058-fbcf-4b0f-86bb-81f5db9e60e4)

  ## Screenshots
  > [!TIP]  
  > | Search screen | Loading screen | Home screen | Details screen | Error screen |
  > |-|-|-|-|-|
  > | <img src="https://github.com/user-attachments/assets/367c3303-1a27-4311-a48d-adba02c49a56" width=250> | <img src="https://github.com/user-attachments/assets/ff7c624e-d8f7-4d34-8cf5-21a84f82c6f9" width=250> | <img src="https://github.com/user-attachments/assets/5ea34217-370e-4b65-9d41-14f723a8217b" width=250> | <img src="https://github.com/user-attachments/assets/74927779-f5ef-46bc-8ba1-99db9a7e4331" width=250> | <img src="https://github.com/user-attachments/assets/b6efde3f-a53e-46b9-a4ee-cbee9bf7c711" width=250>
  > | <img src="https://github.com/user-attachments/assets/69aa8bc6-27bb-4e85-82da-f47744008458" width=250> | <img src="https://github.com/user-attachments/assets/518bf1d9-0a7a-43e5-9799-0670a9ef2937" width=250> | <img src="https://github.com/user-attachments/assets/ea16ea53-f60b-4999-9e45-234f4133bbba" width=250> | <img src="https://github.com/user-attachments/assets/9c51b1d8-e86a-402e-8ade-b5d589de575d" width=250> | <img src="https://github.com/user-attachments/assets/cb30ad2e-a47f-4c2a-b703-e285d0d8830c" width=250>

## Installation

1. Clone the repository:
 
```
  git clone https://github.com/nabilBouzineDev/Book-Finder-App.git
```
2. Open the project in Android Studio.

3. Build and run the project on an emulator or physical device.

# Links

_**Get Started? The main resources of this project:**_

[![codelab](https://img.shields.io/badge/Codelab-white.svg?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAIAAAD9b0jDAAABkElEQVR4Ae2UA4weQQBGt4jRxqptK6iD2jbjpBfUtnW2bdu2bd9v299lkrOtye54Xt6Q0o5CGEvoDHQGqlKJfDxk6Skd62KqMx1yg5Rq1VCgkrBg+u5V+GhLKEl0BKkMKE2g9PdTpifxeRVHDwKqKC9jXjxK37qYcXgnPvqeNbw/30jTyxgT4OZZXcGHzCaHhwW0yv6gfIbw1SP62vlgtRFBV9FaSHs9jwZWGxcfxO8HfeVIhT1D7aPlF/XKHO9Y8/ctBAsf6HxT/e4iXxNswQKxTRlFk3TPrtCCWuXSp8JDn0X4jn9gx597In/+AOLaXgLULgb9JNA2ZSxFr1B8u9+J9Fw1TKG21yBt0WZfS/Nf2IqzuN4zlEwfXODauCgaB8t6OGlFH5XBlDJyrjK8Nbb32LzbfE8P0ycBahDsooyvma1uEwSIsMgHOpRR38+RwlKc/yPqqNzmC8d2ImRjN2i4BYM4/GHZCqIJceRJpbrRHywii/wQ775PijytTNmxRk2LUdfY45pO8ldqBjoD1QHbRmflwD8YjgAAAABJRU5ErkJggg==)](https://developer.android.com/codelabs/basic-android-kotlin-compose-bookshelf#0)
[![google](https://img.shields.io/badge/Books_API-fff?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAADmUlEQVR4Ab1XA5AjQRQ9G6UrnV0627Zt27Zt27Zt27Z9FyeXiZN3v1NzW9vTmd3J6lW9cKbf6690EmlFIBDIQGzr9/vXE+8RjUSPTIP82Xq6pg27VvPCGoTz08KbiE5oBF3rIG6ge/PGWBhAGlpkAdEHzRCMeInzyEjqcHedj258gbgBM/KU1syjVbwo3aBHCPh+foe0YyMsw/vC0KI2dDVLQ1ejFAzNasI8tDekLWvh+/5VzYQOQGENOxfFfb9+wDplNHTVS0JXrUTUpGusk0aye0Ka4CPBi6cNFXbn2RPQ16vIi2igvn4luC6dVUuHWBOs4KCAtGtzOKICbXOnqKVjbqhW8yl2rrqwqUcb2Nctg/P4QThPHKLXy2Hq2ZYXXzgTCARUu4NLBetzZbHp61YQhI3tG8N9/zbU4L53C4bW9fF3+XwtnbH+f79nVA4Z++IBgri5fxcEbDZEh4Bk19qaEkUhPQt/W24Bxwd4z6SEdVSOCHHWcn6LCXEN0m7Fwr+ec/ZpJrznkwcpLcsEfZ3icJ4+hvgAaa9hBu5x+X9Qk4lH0H0wJ+D1xpeB28yAEZHgvZqFM+B71gZRoeoMu2ZeeuEVBhMz4OEMXEjJGfC/nxBnBnbc8CgNuBPUwKYr7pAGDFGm4GnrODOw97YQAZ1YhA9rcQa+Xy0Ij9+LcPFR5xcMXHnlFYtQbMPZEeLHTuRE+d2NcfjDeYSL3bc8goFfloDYhuwMxw+iT3CcT42Zh4uh+O5mQdY83BUGpxla8dcZQPPFEifeeY0j1CBqGTxskhPu20W3pjJhju3PjIDFHf0o9viAUbucwu73iPmXAKT7/2O0AZHw065Dxf3tBBP1j/bC1R/3oYa35s/odmQ3CVo5cRYNh1sI/9rIP8d5/X6+0k59vioLi2x+ciAWPNyEvW9PYd+701jxdAe6nx+HErubB78vtXkUqsz5FmHg2mufUtxDmrmVB5J5UGDb6yOCuFaW2NEZFRfeFXpfNjBL7Rj+DAqc+XKd0tE+bAOl9rTEuqdHQok/BpBK7VyYRz69cvgl6THu5iKU3NNck3ivixPxyvQhlPhvALmi+0NSmDfBF+eWV4fQ//JU1DnSA6X3tmQ7pTbthh4XJgRr4bXpI2QI4rTBQurKYiSeIo7Awi7sXIOJ1Oz0SvTGQthDnKWW83CisZ4ohSEsEdfKrRY3YAdIYis2v4m3iTqiW6ZO/mwNG6/yhNOEf6HhfzYhUKeuAAAAAElFTkSuQmCC)](https://developers.google.com/books)
[![github](https://img.shields.io/badge/Amphibians-000?style=for-the-badge&logo=git&logoColor=white)](https://github.com/nabilBouzineDev/Amphibians)

_**Any Question? Contact Me:**_

[![LinkedIn Badge](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=fff)](https://www.linkedin.com/in/nabilbdev)
[![Gmail Badge](https://img.shields.io/badge/Gmail-EA4335?style=for-the-badge&logo=gmail&logoColor=fff)](mailto:nabilsocialnetwork@gmail.com)
