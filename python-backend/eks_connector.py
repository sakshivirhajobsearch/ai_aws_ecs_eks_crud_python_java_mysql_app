import boto3

def fetch_eks_clusters():
    client = boto3.client('eks')
    response = client.list_clusters()
    return response['clusters']
