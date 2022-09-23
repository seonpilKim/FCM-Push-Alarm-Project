## 🎮FCM Push 알림(Web) 기능 개발 토이 프로젝트
![image](https://user-images.githubusercontent.com/68049320/191960440-32bc004a-edd3-4580-b8fc-90b0d2468825.png)

### FCM 이란?
- **FCM**은 **Firebase Cloud Messaging**의 약자로, 무료로 메시지를 안정적으로 전송할 수 있는 교차 플랫폼 메시징 솔루션이다.
- 모든 사용자에게 알림 메시지를 전송할 수도 있고, 그룹을 지어 메시지를 전송할 수도 있다.
- IOS, AOS, Web 등의 다양한 플랫폼을 지원하며, 플랫폼에 종속되지 않고 Push 메시지를 전송할 수 있다.

### FCM 동작 원리
#### 1. 클라이언트가 Device Token를 요청하는 과정
![image](https://user-images.githubusercontent.com/68049320/191959375-c5af6e5a-9794-454b-85b3-b045d2eba7c3.png)
- 클라이언트가 FCM으로부터 Unique Device Token을 발급 받고, 서버는 이 토큰을 이용하여 기기를 식별하고, 메시지를 보낼 수 있게 된다.
- 서버는 이 토큰을 DB에 저장해 두었다가, 나중에 메시지를 보낼 때 꺼내어 사용한다.

#### 2. 서버에서 메시지를 전달하는 과정
![image](https://user-images.githubusercontent.com/68049320/191959825-1f32f20b-c299-411c-af3d-f2f8749082cc.png)
- 서버에서 DB에 저장되어 있는 토큰을 꺼내고, FCM API를 호출하여 특정 Device로 메시지를 전달한다.

### Reference
- https://firebase.google.com/docs/cloud-messaging
- https://firebase.google.com/docs/reference
