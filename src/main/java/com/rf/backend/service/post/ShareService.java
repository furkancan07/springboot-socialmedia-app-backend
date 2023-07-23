package com.rf.backend.service.post;

import com.rf.backend.entity.post.Share;
import com.rf.backend.repository.post.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShareService{
    ShareRepository shareRepository;
    List<Share> shares=new ArrayList<>();


    public List<Share> getAllShares(){
        return  shares;
    }
@Autowired
    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public void Kaydet(Share share){
    shareRepository.save(share);
    }
    public boolean existingShare(Long id){
    return  shareRepository.existsById(id);
    }
    public Share findById(Long id){
        return shareRepository.findById(id).orElse(null);
    }
    public void  sil(Long id){

        for (Share share :getAllShares()) {
            if(share.getId().equals(id)){
                shareRepository.delete(share);
                shares.remove(share);
                break;
            }
        }

    }


}
