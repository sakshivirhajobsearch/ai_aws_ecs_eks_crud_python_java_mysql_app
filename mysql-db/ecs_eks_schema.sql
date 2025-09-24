CREATE DATABASE IF NOT EXISTS ai_aws_ecs_eks;
USE ai_aws_ecs_eks;

CREATE TABLE ecs_clusters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cluster_arn VARCHAR(255)
);

CREATE TABLE eks_clusters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cluster_name VARCHAR(255)
);
