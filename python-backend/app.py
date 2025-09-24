from flask import Flask, request, jsonify
from ai_predictor import predict_cluster_risk

app = Flask(__name__)

# ECS Risk Prediction Endpoint (POST only)
@app.route('/api/ecs/risk', methods=['POST'])
def predict_ecs_risk():
    data = request.json
    cpu = data.get('cpu', 0)
    memory = data.get('memory', 0)
    services = data.get('services', 0)
    risk = predict_cluster_risk(cpu, memory, services)
    return jsonify({'risk': risk})

# EKS Risk Prediction Endpoint (POST only)
@app.route('/api/eks/risk', methods=['POST'])
def predict_eks_risk():
    data = request.json
    cpu = data.get('cpu', 0)
    memory = data.get('memory', 0)
    pods = data.get('pods', 0)
    risk = predict_cluster_risk(cpu, memory, pods)
    return jsonify({'risk': risk})

# Optional: root check
@app.route('/')
def home():
    return "AI ECS/EKS Risk Prediction API is running."

if __name__ == '__main__':
    app.run(debug=True)
