import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import axios from "axios";


function requestPermission() {
  console.log("Requesting permission...");
  Notification.requestPermission().then((permission) => {
    if (permission === "granted") {
        const config = {
            apiKey: "AIzaSyB07FHn8P3Pkf4RcVwiesGuMgavsFvRY1Y",
            authDomain: "push-8c2a6.firebaseapp.com",
            projectId: "push-8c2a6",
            storageBucket: "push-8c2a6.appspot.com",
            messagingSenderId: "1081563866814",
            appId: "1:1081563866814:web:b819265c1707147ca7a0ef",
            measurementId: "G-7D4T3SP28R",
          };
      console.log("Notification permission granted");
      const app = initializeApp(config);
      const messaging = getMessaging();

      // Get Token
      getToken(messaging, {
        vapidKey:process.env.REACT_APP_VAPID_KEY,
      })
        .then((currentToken) => {
          if (currentToken) {
            console.log("token:", currentToken);
            axios.post("http://localhost:8080/push?token=" + currentToken, {});
          } else {
            console.log(
              "No registration token available. Request permission to generate one."
            );
          }
        })
        .catch((err) => {
          console.log("An error occurred while retrieving token. ", err);
          // ...
        });
      // Foreground Receive Message
      onMessage(messaging, (payload) => {
        console.log("Message received. ", payload);
        // ...
      });
    } else {
      console.log("Do not have permission");
    }
  });
}

requestPermission();
