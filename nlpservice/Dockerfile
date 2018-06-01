FROM python:3

# Set the working directory to /
WORKDIR /nlpservice

# Copy the current directory contents into the container at /nlpservice
ADD app.py /nlpservice
ADD encode.py /nlpservice
ADD requirements.txt /nlpservice
ADD sophie/ /sophie

RUN pip install -r requirements.txt
RUN cd /sophie && python setup.py install
RUN python -m spacy download en

# Make port 80 available to the world outside this container
EXPOSE 80

# Run app.py when the container launches
CMD ["python", "/nlpservice/app.py"]