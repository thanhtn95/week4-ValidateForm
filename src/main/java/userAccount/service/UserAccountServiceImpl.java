package userAccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import userAccount.model.UserAccount;
import userAccount.repository.UserAccountRepository;

public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }
}
