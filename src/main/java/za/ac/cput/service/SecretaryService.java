package za.ac.cput.service;

import za.ac.cput.domain.Secretary;

import java.util.Set;

public interface SecretaryService extends IService<Secretary, String>{
    Set<Secretary> getAll();
}
