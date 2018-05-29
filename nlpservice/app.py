import json

from flask import Flask
from flask import request
from flask import jsonify
from flask import g

from sophie import Extractor
from encode import RelationEncoder

app = Flask(__name__)
extractor = Extractor()

@app.route("/relations")
def get_relations():
    text = request.args.get('text');
    relations = extractor.get_relations(text)
    
    relation_list = list()
    for relation in relations:
        relation_list.append(relation)
        
    return json.dumps(relation_list, cls=RelationEncoder)

app.run(debug=False)