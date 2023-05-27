    public int savePost(MultipartFile image, String comment){
        int count = 0;
        System.out.println("SAVING POST INTO DB");
        try{
            InputStream is = image.getInputStream();
            count = jdbcTemplate.update(Queries.SQL_INSERT_POST, is, comment);
    }
    catch (Exception ex){
        ex.printStackTrace();
    }
    return count;
}