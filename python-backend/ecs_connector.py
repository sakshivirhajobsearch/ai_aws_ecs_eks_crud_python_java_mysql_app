import boto3
from db import get_connection

def fetch_ecs_clusters():
    client = boto3.client('ecs')
    response = client.list_clusters()
    cluster_arns = response['clusterArns']
    return cluster_arns
