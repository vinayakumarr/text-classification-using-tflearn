'''
written by vinayakumar R (https://github.com/vinayakumarr)

Basic text classification code using LSTM with only two class: positive and negative

Dataset is taken from https://github.com/dennybritz/cnn-text-classification-tf/tree/master/data/rt-polaritydata

The poitivie and negative reviews are merged into one single file. for positive labelled as 1 and negative labelled as 0. You can also find code for preprocessing
'''

from __future__ import print_function
import numpy as np
import tflearn
import pandas as pd
from tflearn.data_utils import to_categorical, pad_sequences, VocabularyProcessor

print("Loading")

with open('polarity.txt') as f:
    content = f.readlines()
#print(content)

with open('classlabel.txt') as f:
    content1 = f.readlines()
#print(content1)

tweets = content
max_tweet_length = 120
min_frequency = 2 
vp = tflearn.data_utils.VocabularyProcessor(max_tweet_length, min_frequency=min_frequency)
vp = vp.fit(tweets)
val = len(vp.vocabulary_)
print(val)
tweets_parsed = vp.transform(tweets)
vp.save('my_dictionary')
print(vp)

trainX = tweets_parsed
trainY = tflearn.data_utils.to_categorical(content1, nb_classes=0)

filtered_gen = (item for item in trainX)
gen_to_list = list(filtered_gen)

trainX1 = pad_sequences(gen_to_list, maxlen=120, value=0.)
#print(trainX1)



# Network building
net = tflearn.input_data([None, 120])
net = tflearn.embedding(net, input_dim=val, output_dim=64)
net = tflearn.lstm(net, 64)
net = tflearn.dropout(net, 0.5)
net = tflearn.fully_connected(net, 2, activation='softmax')
net = tflearn.regression(net, optimizer='adam',loss='binary_crossentropy')


# Training
model = tflearn.DNN(net, clip_gradients=0., tensorboard_verbose=0)
model.fit(trainX1, trainY, show_metric=True, batch_size=64)

