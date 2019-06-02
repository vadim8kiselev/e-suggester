from __future__ import print_function

import keras

from keras.callbacks import ModelCheckpoint
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD

import csv
import numpy as np

trained = False

parameters = 10
num_classes = 13

batch_size = 1
epochs = 1

hidden_layer_neurons = 256
hidden_layer_activation = 'sigmoid'

loss = 'categorical_crossentropy'
optimizer = SGD()

model = Sequential()
model.add(Dense(hidden_layer_neurons, activation=hidden_layer_activation, input_shape=(parameters,)))
model.add(Dense(hidden_layer_neurons, activation=hidden_layer_activation))
model.add(Dense(num_classes, activation='softmax'))

model.summary()

model.compile(loss=loss,
              optimizer=optimizer,
              metrics=['accuracy'])


def train():
    global trained
    if not trained:
        with open("dataset/dataset_1.csv", 'r') as f:
            rows = list(csv.reader(f, delimiter=";"))

        dataset = np.array([row[:-1] for row in rows]).astype('int')
        result = np.array([row[-1:] for row in rows]).astype('int')

        dataset = dataset.reshape(len(dataset), parameters)
        print(dataset.shape[0], 'Train samples')

        result = keras.utils.to_categorical(result, num_classes)

        model.fit(dataset, result,
                            batch_size=batch_size,
                            epochs=epochs,
                            verbose=1)

        score = model.evaluate(dataset, result, verbose=0)

        print()
        print('Dataset size:', len(dataset))
        print('Batch size:', batch_size)
        print('Epochs:', epochs)
        print('Number of hidden neurons:', hidden_layer_neurons)
        print('Activation function:', hidden_layer_activation)
        print('Loss function:', loss)
        print('Optimizer:', optimizer.decay.name)
        print()
        print('Loss:', score[0])
        print('Accuracy:', score[1])

        trained = True


def predict(function):
    vector = np.array([int(digit) for digit in function.split(';')])
    vector = vector.reshape(1, 10)
    predicted_class = np.argmax(model.predict(vector))
    return str(predicted_class)
