def predict_cluster_risk(cpu, memory, services_or_pods):
    """
    Predicts the risk level of an ECS or EKS cluster based on resource usage.

    Args:
        cpu (float): CPU usage percentage (0-100)
        memory (float): Memory usage percentage (0-100)
        services_or_pods (int): Number of services (for ECS) or pods (for EKS)

    Returns:
        str: Risk level - "Low", "Medium", or "High"
    """
    # Normalize inputs
    cpu = float(cpu)
    memory = float(memory)
    services_or_pods = int(services_or_pods)

    # Weighted scoring logic
    score = (cpu * 0.4) + (memory * 0.4) + (services_or_pods * 0.2)

    # Determine risk level based on score
    if score >= 75:
        return "High"
    elif score >= 50:
        return "Medium"
    else:
        return "Low"
