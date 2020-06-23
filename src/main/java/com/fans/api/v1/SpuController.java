package com.fans.api.v1;

import com.fans.core.exception.http.NotFountException;
import com.fans.entity.Spu;
import com.fans.service.ISpuService;
import com.fans.vo.PagingDozer;
import com.fans.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;

/**
 * className: SpuController
 *
 * @author k
 * @version 1.0
 * @description spu控制层
 * @date 2020-05-30 16:35
 **/
@RestController
@RequestMapping(value = "/spu")
@Validated
public class SpuController {

    @Resource(name = "iSpuService")
    private ISpuService iSpuService;

    @GetMapping(value = "/id/{id}/detail")
    public Spu getDetail(@PathVariable(name = "id") @Positive(message = "{id.positive}") Long id) {
        Spu spu = iSpuService.getSpuById(id);
        if (spu == null) {
            throw new NotFountException(30003);
        }
        return spu;
    }

    @GetMapping(value = "/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable(name = "id") @Positive Long id) {
        Spu spu = iSpuService.getSpuById(id);
        if (spu == null) {
            throw new NotFountException(30003);
        }
        SpuSimplifyVO spuSimplifyVO = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, spuSimplifyVO);
        return spuSimplifyVO;
    }

    @GetMapping(value = "/latest")
    public PagingDozer<Spu, SpuSimplifyVO> getLatestSpuList(@RequestParam(name = "start", defaultValue = "0", required = false) Integer start,
                                                            @RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        Page<Spu> spuList = iSpuService.getLatestPagingSpu(start / count, count);
        return new PagingDozer<>(spuList, SpuSimplifyVO.class);
    }

    @GetMapping(value = "/hotLatest")
    public PagingDozer<Spu, SpuSimplifyVO> getHotLatestSpuList(@RequestParam(name = "start", defaultValue = "0", required = false) Integer start,
                                                               @RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        Page<Spu> spuList = iSpuService.getHotLatestPagingSpu(start / count, count);
        return new PagingDozer<>(spuList, SpuSimplifyVO.class);
    }

    @GetMapping(value = "/by/category/{id}")
    public PagingDozer<Spu, SpuSimplifyVO> getByCategory(@PathVariable(name = "id") Long categoryId,
                                                         @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                                         @RequestParam(name = "start", defaultValue = "0", required = false) Integer start,
                                                         @RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        Page<Spu> spuList = iSpuService.getByCategory(categoryId, isRoot, start / count, count);
        return new PagingDozer<>(spuList, SpuSimplifyVO.class);
    }

}
