package healthblog.services;

import healthblog.models.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceStubImpl implements TagService {
    //private TagRepository tagRepository;

    /*@Autowired
    /public TagServiceStubImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }*/

    public Tag findTag(String name) {
        //return this.tagRepository.findByName(name);
        return null; //TODO
    }

    public void saveTag(Tag tag) {
        //this.tagRepository.saveAndFlush(tag);
        //TODO
    }

    public List<Tag> getAllTags() {
        //return this.tagRepository.findAll();
        return null; //TODO
    }
}
