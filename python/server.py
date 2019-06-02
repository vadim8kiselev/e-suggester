from network import *
from flask import request, make_response
import connexion

app = connexion.App(__name__, specification_dir='./')


@app.route('/predict')
def predict_endpoint():
    function = request.args.get('function')
    return make_response(predict(function))


if __name__ == '__main__':
    train()
    app.run(host='127.0.0.1', port=8888, debug=True)
