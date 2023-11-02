<!-- RUN JENKIN -->
// BUILD
docker image build -t jenkins-docker .

docker run -it -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped jenkins-docker

<!-- INSTALL NGROK -->
<!-- STEP 1 -->
 curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list && sudo apt update && sudo apt install ngrok
<!-- STEP 2 -->
ngrok config add-authtoken 2Xbw9OOIcJJBlu24FcKRfk8MVb6_622BmCEgjFTGbUnLrRhZ3
<!-- STEP 3 -->
ngrok http 8080