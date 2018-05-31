FROM java:8

# Set the working directory to /appservice
WORKDIR /appservice

# Copy the current directory contents into the container at /appservice
ADD target/web-1.0.0.jar /appservice/web-1.0.0.jar

# Make port 80 available to the world outside this container
EXPOSE 8082

# Run app.py when the container launches
CMD ["java", "-jar", "/appservice/web-1.0.0.jar"]