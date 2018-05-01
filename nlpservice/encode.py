import json
from sophie import Relation

class RelationEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Relation):
            return {
                "type": obj.type,
                "subj": {
                    "text": obj.subj.text,
                    "type": obj.subj.type
                },
                "obj": {
                    "text": obj.obj.text,
                    "type": obj.obj.type
                }
            }
        return super(RelationEncoder, self).default(obj)