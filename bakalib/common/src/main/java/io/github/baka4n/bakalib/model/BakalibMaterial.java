package io.github.baka4n.bakalib.model;

import de.javagl.obj.FloatTuple;
import de.javagl.obj.Mtl;
import de.javagl.obj.TextureOptions;

import java.util.List;

public class BakalibMaterial implements Mtl {
    private final Mtl mtl;
    private int tinIndex = -1;
    private boolean useDiffuseColor = false;

    public BakalibMaterial(Mtl mtl) {
        this.mtl = mtl;
    }


    @Override
    public String getName() {
        return mtl.getName();
    }

    @Override
    public Integer getIllum() {
        return mtl.getIllum();
    }

    @Override
    public void setIllum(Integer illum) {
        mtl.setIllum(illum);
    }

    @Override
    public Float getNi() {
        return mtl.getNi();
    }

    @Override
    public void setNi(Float ni) {
        mtl.setNi(ni);
    }

    @Override
    public FloatTuple getTf() {
        return mtl.getTf();
    }

    @Override
    public void setTf(Float r, Float g, Float b) {
        mtl.setTf(r, g, b);
    }

    @Override
    public Float getSharpness() {
        return mtl.getSharpness();
    }

    @Override
    public void setSharpness(Float sharpness) {
        mtl.setSharpness(sharpness);
    }

    @Override
    public FloatTuple getKa() {
        return mtl.getKa();
    }

    @Override
    public void setKa(Float r, Float g, Float b) {
        mtl.setKa(r, g, b);
    }

    @Override
    public String getMapKa() {
        return mtl.getMapKa();
    }

    @Override
    public void setMapKa(String mapKa) {
        mtl.setMapKa(mapKa);
    }

    @Override
    public TextureOptions getMapKaOptions() {
        return mtl.getMapKaOptions();
    }

    @Override
    public void setMapKaOptions(TextureOptions options) {
        mtl.setMapKaOptions(options);
    }

    @Override
    public FloatTuple getKd() {
        return mtl.getKd();
    }

    @Override
    public void setKd(Float r, Float g, Float b) {
        mtl.setKd(r, g, b);
    }

    @Override
    public String getMapKd() {
        return mtl.getMapKd();
    }

    @Override
    public void setMapKd(String mapKd) {
        mtl.setMapKd(mapKd);
    }

    @Override
    public TextureOptions getMapKdOptions() {
        return mtl.getMapKdOptions();
    }

    @Override
    public void setMapKdOptions(TextureOptions options) {
        mtl.setMapKdOptions(options);
    }

    @Override
    public FloatTuple getKs() {
        return mtl.getKs();
    }

    @Override
    public void setKs(Float r, Float g, Float b) {
        mtl.setKs(r, g, b);
    }

    @Override
    public String getMapKs() {
        return mtl.getMapKs();
    }

    @Override
    public void setMapKs(String mapKs) {
        mtl.setMapKs(mapKs);
    }

    @Override
    public TextureOptions getMapKsOptions() {
        return mtl.getMapKsOptions();
    }

    @Override
    public void setMapKsOptions(TextureOptions options) {
        mtl.setMapKsOptions(options);
    }

    @Override
    public Float getNs() {
        return mtl.getNs();
    }

    @Override
    public void setNs(Float ns) {
        mtl.setNs(ns);
    }

    @Override
    public String getMapNs() {
        return mtl.getMapNs();
    }

    @Override
    public void setMapNs(String mapNs) {
        mtl.setMapNs(mapNs);
    }

    @Override
    public TextureOptions getMapNsOptions() {
        return mtl.getMapNsOptions();
    }

    @Override
    public void setMapNsOptions(TextureOptions options) {
        mtl.setMapNsOptions(options);
    }

    @Override
    public Float getD() {
        return mtl.getD();
    }

    @Override
    public void setD(Float d) {
        mtl.setD(d);
    }

    @Override
    public Boolean isHalo() {
        return mtl.isHalo();
    }

    @Override
    public void setHalo(Boolean halo) {
        mtl.setHalo(halo);
    }

    @Override
    public String getMapD() {
        return mtl.getMapD();
    }

    @Override
    public void setMapD(String mapD) {
        mtl.setMapD(mapD);
    }

    @Override
    public TextureOptions getMapDOptions() {
        return mtl.getMapDOptions();
    }

    @Override
    public void setMapDOptions(TextureOptions options) {
        mtl.setMapDOptions(options);
    }

    @Override
    public String getBump() {
        return mtl.getBump();
    }

    @Override
    public void setBump(String bump) {
        mtl.setBump(bump);
    }

    @Override
    public TextureOptions getBumpOptions() {
        return mtl.getBumpOptions();
    }

    @Override
    public void setBumpOptions(TextureOptions options) {
        mtl.setBumpOptions(options);
    }

    @Override
    public String getDisp() {
        return mtl.getDisp();
    }

    @Override
    public void setDisp(String disp) {
        mtl.setDisp(disp);
    }

    @Override
    public TextureOptions getDispOptions() {
        return mtl.getDispOptions();
    }

    @Override
    public void setDispOptions(TextureOptions options) {
        mtl.setDispOptions(options);
    }

    @Override
    public String getDecal() {
        return mtl.getDecal();
    }

    @Override
    public void setDecal(String decal) {
        mtl.setDecal(decal);
    }

    @Override
    public TextureOptions getDecalOptions() {
        return mtl.getDecalOptions();
    }

    @Override
    public void setDecalOptions(TextureOptions options) {
        mtl.setDecalOptions(options);
    }

    @Override
    public List<TextureOptions> getReflOptions() {
        return mtl.getReflOptions();
    }

    @Override
    public Float getPr() {
        return mtl.getPr();
    }

    @Override
    public void setPr(Float pr) {
        mtl.setPr(pr);
    }

    @Override
    public String getMapPr() {
        return mtl.getMapPr();
    }

    @Override
    public void setMapPr(String mapPr) {
        mtl.setMapPr(mapPr);
    }

    @Override
    public TextureOptions getMapPrOptions() {
        return mtl.getMapPrOptions();
    }

    @Override
    public void setMapPrOptions(TextureOptions options) {
        mtl.setMapPrOptions(options);
    }

    @Override
    public Float getPm() {
        return mtl.getPm();
    }

    @Override
    public void setPm(Float pm) {
        mtl.setPm(pm);
    }

    @Override
    public String getMapPm() {
        return mtl.getMapPm();
    }

    @Override
    public void setMapPm(String mapPm) {
        mtl.setMapPm(mapPm);
    }

    @Override
    public TextureOptions getMapPmOptions() {
        return mtl.getMapPmOptions();
    }

    @Override
    public void setMapPmOptions(TextureOptions options) {
        mtl.setMapPmOptions(options);
    }

    @Override
    public Float getPs() {
        return mtl.getPs();
    }

    @Override
    public void setPs(Float ps) {
        mtl.setPs(ps);
    }

    @Override
    public String getMapPs() {
        return mtl.getMapPs();
    }

    @Override
    public void setMapPs(String mapPs) {
        mtl.setMapPs(mapPs);
    }

    @Override
    public TextureOptions getMapPsOptions() {
        return mtl.getMapPsOptions();
    }

    @Override
    public void setMapPsOptions(TextureOptions options) {
        mtl.setMapPsOptions(options);
    }

    @Override
    public Float getPc() {
        return mtl.getPc();
    }

    @Override
    public void setPc(Float pc) {
        mtl.setPc(pc);
    }

    @Override
    public Float getPcr() {
        return mtl.getPcr();
    }

    @Override
    public void setPcr(Float pcr) {
        mtl.setPcr(pcr);
    }

    @Override
    public FloatTuple getKe() {
        return mtl.getKe();
    }

    @Override
    public void setKe(Float r, Float g, Float b) {
        mtl.setKe(r, g, b);
    }

    @Override
    public String getMapKe() {
        return mtl.getMapKe();
    }

    @Override
    public void setMapKe(String mapKe) {
        mtl.setMapKe(mapKe);
    }

    @Override
    public TextureOptions getMapKeOptions() {
        return mtl.getMapKeOptions();
    }

    @Override
    public void setMapKeOptions(TextureOptions options) {
        mtl.setMapKeOptions(options);
    }

    @Override
    public Float getAniso() {
        return mtl.getAniso();
    }

    @Override
    public void setAniso(Float aniso) {
        mtl.setAniso(aniso);
    }

    @Override
    public Float getAnisor() {
        return mtl.getAnisor();
    }

    @Override
    public void setAnisor(Float anisor) {
        mtl.setAniso(anisor);
    }

    @Override
    public String getNorm() {
        return mtl.getNorm();
    }

    @Override
    public void setNorm(String norm) {
        mtl.setNorm(norm);
    }

    @Override
    public TextureOptions getNormOptions() {
        return mtl.getNormOptions();
    }

    @Override
    public void setNormOptions(TextureOptions options) {
        mtl.setNormOptions(options);
    }

    public int getTinIndex() {
        return tinIndex;
    }

    public void setTinIndex(int tinIndex) {
        this.tinIndex = tinIndex;
    }

    public boolean isUseDiffuseColor() {
        return useDiffuseColor;
    }

    public void setUseDiffuseColor() {
        this.useDiffuseColor = true;
    }
}
