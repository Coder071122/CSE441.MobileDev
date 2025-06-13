<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class News extends Model
{
    protected $fillable = [
        'news_code',
        'title',
        'content',
        'image',
        'author_uid',
        'timestamp'
    ];

    protected $casts = [
        'timestamp' => 'datetime'
    ];

    public function author()
    {
        return $this->belongsTo(User::class, 'author_uid', 'id');
    }
}
