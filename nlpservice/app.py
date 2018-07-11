import json

from flask import Flask
from flask import request
from flask import jsonify
from flask import g

from sophie import Extractor
from encode import RelationEncoder
from flask.helpers import make_response

app = Flask(__name__)
extractor = Extractor()

@app.route("/relations")
def get_relations():
    text = request.args.get('text');
    relations = extractor.get_relations(text)
    
    relation_list = list()
    for relation in relations:
        relation_list.append(relation)
    
    json_body = json.dumps(relation_list, cls=RelationEncoder)
    response = make_response(json_body)
    response.headers['Content-Type'] = 'application/json'
    
    return response

if __name__ == "__main__":
	app.run(host='0.0.0.0', port=8082)