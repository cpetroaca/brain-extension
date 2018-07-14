import json

from flask import Flask
from flask import request
from flask import jsonify
from flask import g
from flask import abort

from sophie import Extractor
from encode import RelationEncoder
from flask.helpers import make_response

app = Flask(__name__)
extractor = Extractor()

@app.route("/relations")
def get_relations():
    text = request.args.get('text');
    if text is None:
        abort(400)
    
    relations = extractor.get_relations(text)
    
    relation_list = list()
    for relation in relations:
        relation_list.append(relation)
    
    json_body = json.dumps(relation_list, cls=RelationEncoder)
    response = make_response(json_body)
    response.headers['Content-Type'] = 'application/json'
    
    return response

@app.errorhandler(400)
def handle_bad_request(e):
    return "{ 'error' : 'text attribute is missing'}", 400

if __name__ == "__main__":
	app.run(host='0.0.0.0', port=8082)